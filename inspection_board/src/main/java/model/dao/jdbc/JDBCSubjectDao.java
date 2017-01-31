package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.dao.SubjectDao;
import model.dao.exception.DaoException;
import model.entity.Subject;

public class JDBCSubjectDao implements SubjectDao {
	private static final String INSERT_INTO_SUBJECT = "INSERT INTO subject (name) values (?)";
	private static final String SELECT_BY_ID = "SELECT * FROM subject WHERE id_subject = ?";
	private static final String SELECT_ALL = "SELECT * FROM subject";
	private static final String UPDATE_SUBJECT = "UPDATE subject SET name = ? WHERE id_subject = ? ";
	private static final String DELETE_SUBJECT = "DELETE FROM subject WHERE id_subject = ?";
	private static final String EXCEPTION_MSG_CREATE_NEW_SUBJECT =
            "Exception during writing new subject to database, subject = %s";
	private static final String EXCEPTION_MSG_FIND_SUBJECT_BY_ID = 
			"Exception during finding subject by id = %d";
	private static final String EXCEPTION_MSG_FIND_ALL_SUBJECTS = 
			"Exception during finding all subjects";
	private static final String EXCEPTION_MSG_UPDATE_SUBJECT = 
			"Exception during update subject = %s";
	private static final String EXCEPTION_MSG_DELETE_SUBJECT = 
			"Exception during deleting subject, id = %d";
	
	private Connection connection;

	public JDBCSubjectDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long create(Subject subject) {

		long result = 0;
		try (PreparedStatement st = connection.prepareStatement(INSERT_INTO_SUBJECT,
				Statement.RETURN_GENERATED_KEYS);) {
			st.setString(1, subject.getName());
			st.executeUpdate();
			try (ResultSet key = st.getGeneratedKeys();) {
				if (key.next()) {
					result = key.getInt(1);
				}
			}
		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_CREATE_NEW_SUBJECT, subject);
            throw new DaoException(message, e);
		}
		return result;
	}

	@Override
	public Optional<Subject> find(long id) {
		Optional<Subject> result = Optional.empty();

		try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID);) {
			st.setLong(1, id);
			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					Subject subject = getSubjectFromResultSet(rs);
					result = Optional.of(subject);
				}
			}
		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_FIND_SUBJECT_BY_ID, id);
            throw new DaoException(message, e);
		}
		return result;
	}

	private Subject getSubjectFromResultSet(ResultSet rs) throws SQLException {
		return new Subject.Builder().setId(rs.getLong("id_subject")).setName(rs.getString("name")).build();
	}

	@Override
	public List<Subject> findAll() {
		List<Subject> subjectList = new ArrayList<Subject>();

		try (Statement st = connection.createStatement();) {
			try (ResultSet rs = st.executeQuery(SELECT_ALL);) {
				while (rs.next()) {
					subjectList.add(getSubjectFromResultSet(rs));
				}
			}

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_FIND_ALL_SUBJECTS);
            throw new DaoException(message, e);
		}

		return subjectList;
	}

	@Override
	public void update(Subject subject) {
		try (PreparedStatement st = connection.prepareStatement(UPDATE_SUBJECT);) {
			st.setString(1, subject.getName());
			st.setLong(2, subject.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_UPDATE_SUBJECT, subject);
            throw new DaoException(message, e);
		}
	}

	@Override
	public void delete(long id) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_SUBJECT);) {
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_DELETE_SUBJECT, id);
            throw new DaoException(message, e);
		}
	}

}
