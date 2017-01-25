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
	
	private static final String INSERT_INTO_SUBJECT = "INSERT INTO subject (name) values (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM subject WHERE id_subject = ?";
	private static final String SELECT_ALL = "SELECT * FROM subject";
	private static final String UPDATE_SUBJECT = "UPDATE subject SET name = ? WHERE id_subject = ? ";
	private static final String DELETE_SUBJECT = "DELETE FROM subject WHERE id_subject = ?";
	
	private Connection connection;
	
	JDBCSubjectDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long create(Subject subject) {
		
		long result = 0;
		try (PreparedStatement st = connection.prepareStatement(INSERT_INTO_SUBJECT, Statement.RETURN_GENERATED_KEYS);) {
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

		try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID);) {
			st.setLong(1, id);
			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					subject = new Subject(rs.getLong(1), rs.getString(2));
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
			try (ResultSet rs = st.executeQuery(SELECT_ALL);) {
				while (rs.next()) {
					subject.add(new Subject(rs.getLong(1), rs.getString(2)));
				}
			}

		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}

		return subject;
	}

	@Override
	public void update(Subject subject) {
		try (PreparedStatement st = connection.prepareStatement(UPDATE_SUBJECT);) {
			st.setString(1, subject.getName());
			st.setLong(2, subject.getId());

			st.executeUpdate();
		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}
	}

	@Override
	public void delete(long id) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_SUBJECT);) {
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}
	}

}
