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

	@Override
	public long create(Subject subject) {
		// try (Connection connection = dataSource.getConnection();
		// Statement statement = connection.createStatement()) {
		// try (ResultSet resultSet = statement.executeQuery("some query")) {
		// // Do stuff with the result set.
		// }
		// try (ResultSet resultSet = statement.executeQuery("some query")) {
		// // Do more stuff with the second result set.
		// }
		// }
		
		long result = 0;
		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn.prepareStatement("INSERT INTO subject (name) values (?)",
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

		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn.prepareStatement("SELECT * FROM subject WHERE id_subject = ?");) {
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

		try (Connection cn = JdbcConnection.getInstance().getConnection(); Statement st = cn.createStatement();) {
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
		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn.prepareStatement("UPDATE subject SET name = ? WHERE id_subject = ? ");) {
			st.setString(1, subject.getName());
			st.setLong(2, subject.getId());

			st.executeUpdate();
		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}
	}

	@Override
	public void delete(long id) {
		try (Connection cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = cn.prepareStatement("DELETE FROM subject WHERE id_subject = ?");) {
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e1) {
			logger.error(e1.getStackTrace());
		}

	}

}
