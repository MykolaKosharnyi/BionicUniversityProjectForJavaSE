package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.dao.SheetDao;
import model.dao.exception.DaoException;
import model.entity.Department;
import model.entity.Sheet;
import model.entity.User;

public class JDBCSheetDao implements SheetDao {
	private static final String INSERT_INTO_SHEET = "INSERT INTO sheet (id_enrollee, id_department) values (?,?)";
	private static final String SELECT_BY_ID = "SELECT id_department, id_enrollee FROM sheet; ";
	private static final String DELETE_FROM_SHEET_BY_DEPARTMENT = "DELETE FROM sheet WHERE id_department = ?";
	private static final String DELETE_FROM_SHEET_BY_ENROLLEE = "DELETE FROM sheet WHERE id_enrollee  = ?";
	private static final String DELETE_FROM_SHEET_BY_ENROLLEE_AND_DEPARTMENT = "DELETE FROM sheet WHERE id_enrollee  = ? and id_department = ?";
	private static final String EXCEPTION_MSG_ADD_USER_TO_SHEET =
			"Exception during adding User with id=%d to sheet, idDepartment=%d";
	private static final String EXCEPTION_MSG_GET_SHEET = 
			"Exception during get sheet from database";
	private static final String EXCEPTION_MSG_DELETE_DEPARTMENT_FROM_SHEET = 
			"Exception during deleting DEPARTMENT from sheet with idDepartment = %d";
	private static final String EXCEPTION_MSG_DELETE_ENROLLEE_FROM_SHEET = 
			"Exception during deleting ENROLLEE frim sheet with idEnrollee = %d";
	private static final String EXCEPTION_MSG_DELETE_ENROLLEE_FROM_DEPARTMENT = 
			"Exception during deleting Enrollee with id=%d from Department wiht id=%d";
	
	private Connection connection;

	public JDBCSheetDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long add(long idEnrollee, long idDepartment) {
		long result = 0;
		try (PreparedStatement st = connection.prepareStatement(INSERT_INTO_SHEET, Statement.RETURN_GENERATED_KEYS)) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idDepartment);
			st.executeUpdate();

			try (ResultSet key = st.getGeneratedKeys()) {
				if (key.next())
					result = key.getInt(1);
			}
		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_ADD_USER_TO_SHEET, idEnrollee, idDepartment);
            throw new DaoException(message, e);
		}
		return result;
	}

	@Override
	public Sheet getSheet() {
		Sheet result = new Sheet();
		Map<Department, List<User>> table = new LinkedHashMap<Department, List<User>>();

		try (PreparedStatement sheetStatement = connection.prepareStatement(SELECT_BY_ID)) {
			table = getSheetTable(sheetStatement.executeQuery());
		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_GET_SHEET);
            throw new DaoException(message, e);
		}
		result.setTable(table);
		return result;
	}

	private Map<Department, List<User>> getSheetTable(ResultSet sheetRS) throws SQLException {
		Map<Department, List<User>> result = new LinkedHashMap<Department, List<User>>();

		JDBCDepartmentDao departmentDAO = new JDBCDepartmentDao(connection);
		List<Department> departmentList = departmentDAO.findAll();

		JDBCUserDao enrolleeDAO = new JDBCUserDao(connection);
		List<User> enrolleeList = enrolleeDAO.findAll();

		while (sheetRS.next()) {

			Department department = getDepartmentFromListById(departmentList, sheetRS.getLong("id_department"));
			User enrollee = getUserFromListById(enrolleeList, sheetRS.getLong("id_enrollee"));

			if (result.containsKey(department)) {
				List<User> listConcreteEnrollee = result.get(department);
				listConcreteEnrollee.add(enrollee);
			} else {
				List<User> listConcreteEnrollee = new ArrayList<>();
				listConcreteEnrollee.add(enrollee);
				result.put(department, listConcreteEnrollee);
			}
		}
		return result;
	}

	private Department getDepartmentFromListById(List<Department> departmentList, long departmentId) {
		Department result;
		Optional<Department> departmentOptional = departmentList.stream().filter(d -> d.getId() == departmentId)
				.findFirst();
		if (departmentOptional.isPresent()) {
			result = departmentOptional.get();
		} else {
			throw new IllegalStateException();
		}
		return result;
	}

	private User getUserFromListById(List<User> enrolleeList, long enrolleeId) {
		User result;
		Optional<User> enrolleeOptional = enrolleeList.stream().filter(d -> d.getId() == enrolleeId).findFirst();
		if (enrolleeOptional.isPresent()) {
			result = enrolleeOptional.get();
		} else {
			throw new IllegalStateException();
		}
		return result;
	}

	@Override
	public void deleteDepartment(long idDepartment) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_FROM_SHEET_BY_DEPARTMENT)) {

			st.setLong(1, idDepartment);
			st.executeUpdate();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_DELETE_DEPARTMENT_FROM_SHEET, idDepartment);
            throw new DaoException(message, e);
		}
	}

	@Override
	public void deleteEnrollee(long idEnrollee) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_FROM_SHEET_BY_ENROLLEE)) {

			st.setLong(1, idEnrollee);
			st.executeUpdate();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_DELETE_ENROLLEE_FROM_SHEET, idEnrollee);
            throw new DaoException(message, e);
		}

	}

	@Override
	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_FROM_SHEET_BY_ENROLLEE_AND_DEPARTMENT)) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idDepartment);
			st.executeUpdate();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_DELETE_ENROLLEE_FROM_DEPARTMENT, idEnrollee, idDepartment);
            throw new DaoException(message, e);
		}
	}

}
