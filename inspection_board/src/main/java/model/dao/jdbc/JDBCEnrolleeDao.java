package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.dao.EnrolleeDao;
import model.entity.Enrollee;

public class JDBCEnrolleeDao implements EnrolleeDao {
	
	static Logger logger = Logger.getLogger(JDBCEnrolleeDao.class);

	private Connection connection;
	
	JDBCEnrolleeDao(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public long create(Enrollee enrollee) {
		long result = 0;

		try (PreparedStatement st = connection.prepareStatement(
						"INSERT INTO enrollee (firstName, secondName, email, phone, password) values (?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);) {

			st.setString(1, enrollee.getFirstName());
			st.setString(2, enrollee.getSecondName());
			st.setString(3, enrollee.getEmail());
			st.setString(4, enrollee.getPhone());
			st.setString(5, enrollee.getPassword());
			st.executeUpdate();

			try (ResultSet key = st.getGeneratedKeys();) {
				if (key.next()) {
					result = key.getInt(1);
				}
				enrollee.setId(result);
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return result;
	}

	@Override
	public Enrollee find(long id) {
		Enrollee enrollee = null;
		
		try(PreparedStatement st = connection.prepareStatement("SELECT * FROM enrollee WHERE ID = ?");){
			st.setLong(1, id);
			
			try(ResultSet rs = st.executeQuery();){
				if (rs.next()) {
					enrollee = new Enrollee(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
				}
			}
			
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return enrollee;
	}

	@Override
	public List<Enrollee> findAll() {
		List<Enrollee> enrollee = new ArrayList<Enrollee>();

		try (Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM enrollee ");) {
			
			while (rs.next()) {
				enrollee.add(new Enrollee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDate(7)));
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		
        return enrollee;
	}

	@Override
	public void update(Enrollee enrollee) {
		try(PreparedStatement st = connection.prepareStatement(
						"UPDATE enrollee SET firstName = ?, secondName = ?, email = ?, phone = ?, password = ? WHERE id = ? ")){
			
			st.setString(1, enrollee.getFirstName());
			st.setString(2, enrollee.getSecondName());
			st.setString(3, enrollee.getEmail());
			st.setString(4, enrollee.getPhone());
			st.setString(5, enrollee.getPassword());
			st.setLong(6, enrollee.getId());
			
            st.executeUpdate();
            
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public void delete(long id) {
		try (PreparedStatement st = connection.prepareStatement("DELETE FROM enrollee WHERE id = ?");) {
			
			st.setLong(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public boolean checkLogin(String email, String password) {
		try(PreparedStatement st = connection.prepareStatement("SELECT * FROM enrollee WHERE email = ? AND password = ?");){
			
			st.setString(1, email);
			st.setString(2, password);
			
			return st.executeQuery().next();
			
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			return false;
		}
	}

	@Override
	public Enrollee findByEmail(String email) {
		Enrollee enrollee = null;

		try (PreparedStatement st = connection.prepareStatement("SELECT * FROM enrollee WHERE email = ?");) {

			st.setString(1, email);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				enrollee = new Enrollee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDate(7));
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}

		return enrollee;
	}

}
