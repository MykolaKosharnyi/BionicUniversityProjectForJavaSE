package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.dao.SubjectDao;
import model.entity.Subject;

public class JDBCSubjectDao implements SubjectDao {

	static Logger logger = Logger.getLogger(JDBCSubjectDao.class);
	
	private Connection connection;
	
	JDBCSubjectDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long create(Subject subject) {
		
		long result = 0;
		try (PreparedStatement st = connection.prepareStatement("INSERT INTO subject (name) values (?)",
						Statement.RETURN_GENERATED_KEYS);) {
			st.setString(1, subject.getName());
			st.executeUpdate();
			try (ResultSet key = st.getGeneratedKeys();) {
				if (key.next()) {
					result = key.getInt(1);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return result;
	}

	@Override
	public Subject find(long id) {
		Subject subject = null;

		try (PreparedStatement st = connection.prepareStatement("SELECT * FROM subject WHERE id_subject = ?");) {
			st.setLong(1, id);
			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					subject = new Subject(rs.getInt(1), rs.getString(2));
				}
			}
		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}

		return subject;
	}

	@Override
	public List<Subject> findAll() {
		List<Subject> subject = new ArrayList<Subject>();

		try (Statement st = connection.createStatement();) {
			try (ResultSet rs = st.executeQuery("SELECT * FROM subject");) {
				while (rs.next()) {
					subject.add(new Subject(rs.getInt(1), rs.getString(2)));
				}
			}

		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}

		return subject;
	}

	@Override
	public void update(Subject subject) {
		try (PreparedStatement st = connection.prepareStatement("UPDATE subject SET name = ? WHERE id_subject = ? ");) {
			st.setString(1, subject.getName());
			st.setLong(2, subject.getId());

			st.executeUpdate();
		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}
	}

	@Override
	public void delete(long id) {
		try (PreparedStatement st = connection.prepareStatement("DELETE FROM subject WHERE id_subject = ?");) {
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}

	}

}
