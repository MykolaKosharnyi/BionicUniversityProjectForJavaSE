package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.dao.UserDao;
import model.dao.exception.DaoException;
import model.entity.Certificate;
import model.entity.User;

public class JDBCUserDao implements UserDao {
	private static final String INSERT_INTO_ENROLLEE = "INSERT INTO enrollee "
			+ "(firstName, secondName, email, phone, password) values (?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM enrollee WHERE ID = ?";
	private static final String SELECT_ALL = "SELECT * FROM enrollee ";
	private static final String UPDATE_ENROLLEE = "UPDATE enrollee SET firstName = ?, "
			+ "secondName = ?, email = ?, phone = ?, password = ? WHERE id = ? ";
	private static final String DELETE_FROM_ENROLLEE = "DELETE FROM enrollee WHERE id = ?";
	private static final String CHECK_LOGIN = "SELECT * FROM enrollee WHERE email = ? AND password = ?";
	private static final String FIND_BY_EMAIL = "SELECT * FROM enrollee WHERE email = ?";
	
	private static final String EXCEPTION_MSG_CREATE_NEW_USER =
            "Exception during writing new user to database, user = %s";
	private static final String EXCEPTION_MSG_FIND_USER_BY_ID =
			"Exception during finding user by id = %d";
	private static final String EXCEPTION_MSG_FIND_ALL_USERS = 
			"Exception during finding all users";
	private static final String EXCEPTION_MSG_UPDATE_USER = 
			"Exception during updating user in database, user = %s";
	private static final String EXCEPTION_MSG_DELETE_USER = 
			"Exception during deleting user from database, userId = %d";
	private static final String EXCEPTION_MSG_CHECK_LOGIN_USER = 
			"Exception during checking login of user, checked email = %s";
	private static final String EXCEPTION_MSG_FIND_BY_EMAIL_USER = 
			"Exception during finding user by email, checked email = %s";

	private Connection connection;

	public JDBCUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long create(User user) {
		long result = 0;

		try (PreparedStatement st = connection.prepareStatement(INSERT_INTO_ENROLLEE,
				Statement.RETURN_GENERATED_KEYS)) {

			st.setString(1, user.getFirstName());
			st.setString(2, user.getSecondName());
			st.setString(3, user.getEmail());
			st.setString(4, user.getPhone());
			st.setString(5, user.getPassword());
			st.executeUpdate();

			try (ResultSet key = st.getGeneratedKeys()) {
				if (key.next()) {
					result = key.getInt(1);
				}
				user.setId(result);
			}

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_CREATE_NEW_USER, user);
            throw new DaoException(message, e);
		}
		return result;
	}

	@Override
	public Optional<User> find(long id) {
		Optional<User> result = Optional.empty();

		try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
			st.setLong(1, id);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					User user = getUserFromResultSet(rs);
					result = Optional.of(user);
				}
			}

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_FIND_USER_BY_ID, id);
            throw new DaoException(message, e);
		}
		return result;
	}

	private User getUserFromResultSet(ResultSet rs) throws SQLException {
		long idUser = rs.getLong("id");
		User user = new User.Builder()
				.setId(idUser)
				.setFirstName(rs.getString("firstName"))
				.setSecondName(rs.getString("secondName"))
				.setEmail(rs.getString("email"))
				.setPhone(rs.getString("phone"))
				.setPassword(rs.getString("password"))
				.setCertificate(getCertificate(idUser)).build();
		return user;
	}

	private Certificate getCertificate(long id) {
		JDBCCertificateDao certificateDao = new JDBCCertificateDao(connection);
		return certificateDao.find(id);
	}

	@Override
	public List<User> findAll() {
		List<User> enrollee = new ArrayList<User>();

		try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(SELECT_ALL);) {

			while (rs.next()) {
				enrollee.add(getUserFromResultSet(rs));
			}

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_FIND_ALL_USERS);
            throw new DaoException(message, e);
		}

		return enrollee;
	}

	@Override
	public void update(User user) {
		try (PreparedStatement st = connection.prepareStatement(UPDATE_ENROLLEE)) {

			st.setString(1, user.getFirstName());
			st.setString(2, user.getSecondName());
			st.setString(3, user.getEmail());
			st.setString(4, user.getPhone());
			st.setString(5, user.getPassword());
			st.setLong(6, user.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_UPDATE_USER, user);
            throw new DaoException(message, e);
		}
	}

	@Override
	public void delete(long id) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_FROM_ENROLLEE)) {

			st.setLong(1, id);
			st.executeUpdate();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_DELETE_USER, id);
            throw new DaoException(message, e);
		}
	}

	@Override
	public boolean checkLogin(String email, String password) {
		try (PreparedStatement st = connection.prepareStatement(CHECK_LOGIN)) {

			st.setString(1, email);
			st.setString(2, password);

			return st.executeQuery().next();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_CHECK_LOGIN_USER, email);
            throw new DaoException(message, e);
		}
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Optional<User> result = Optional.empty();

		try (PreparedStatement st = connection.prepareStatement(FIND_BY_EMAIL)) {

			st.setString(1, email);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				User user = getUserFromResultSet(rs);
				result = Optional.of(user);
			}

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_FIND_BY_EMAIL_USER, email);
            throw new DaoException(message, e);
		}
		return result;
	}

}
