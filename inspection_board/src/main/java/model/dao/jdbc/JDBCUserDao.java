package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.dao.UserDao;
import model.entity.User;

public class JDBCUserDao implements UserDao {
	
	static Logger logger = Logger.getLogger(JDBCUserDao.class);
	
	private static final String INSERT_INTO_ENROLLEE = "INSERT INTO enrollee "
			+ "(firstName, secondName, email, phone, password) values (?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM enrollee WHERE ID = ?";
	private static final String SELECT_ALL = "SELECT * FROM enrollee ";
	private static final String UPDATE_ENROLLEE = "UPDATE enrollee SET firstName = ?, "
			+ "secondName = ?, email = ?, phone = ?, password = ? WHERE id = ? ";
	private static final String DELETE_FROM_ENROLLEE = "DELETE FROM enrollee WHERE id = ?";
	private static final String CHECK_LOGIN = "SELECT * FROM enrollee WHERE email = ? AND password = ?";
	private static final String FIND_BY_EMAIL = "SELECT * FROM enrollee WHERE email = ?";

	private Connection connection;
	
	JDBCUserDao(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public long create(User enrollee) {
		long result = 0;

		try (PreparedStatement st = connection.prepareStatement(INSERT_INTO_ENROLLEE, Statement.RETURN_GENERATED_KEYS);) {

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
	public User find(long id) {
		User enrollee = null;
		
		try(PreparedStatement st = connection.prepareStatement(SELECT_BY_ID);){
			st.setLong(1, id);
			
			try(ResultSet rs = st.executeQuery();){
				if (rs.next()) {
					enrollee = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
				}
			}
			
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return enrollee;
	}

	@Override
	public List<User> findAll() {
		List<User> enrollee = new ArrayList<User>();

		try (Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(SELECT_ALL);) {
			
			while (rs.next()) {
				enrollee.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDate(7)));
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		
        return enrollee;
	}

	@Override
	public void update(User enrollee) {
		try(PreparedStatement st = connection.prepareStatement(UPDATE_ENROLLEE)){
			
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
		try (PreparedStatement st = connection.prepareStatement(DELETE_FROM_ENROLLEE);) {
			
			st.setLong(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public boolean checkLogin(String email, String password) {
		try(PreparedStatement st = connection.prepareStatement(CHECK_LOGIN);){
			
			st.setString(1, email);
			st.setString(2, password);
			
			return st.executeQuery().next();
			
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			return false;
		}
	}

	@Override
	public User findByEmail(String email) {
		User enrollee = null;

		try (PreparedStatement st = connection.prepareStatement(FIND_BY_EMAIL);) {

			st.setString(1, email);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				enrollee = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDate(7));
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}

		return enrollee;
	}

}
