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

import org.apache.log4j.Logger;

import model.dao.SheetDao;
import model.entity.Department;
import model.entity.Enrollee;

public class JDBCSheetDao implements SheetDao {

	static Logger logger = Logger.getLogger(JDBCSheetDao.class);
	
	private Connection connection;
	
	JDBCSheetDao(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public long add(long idEnrollee, long idDepartment) {
		long result = 0;
		try (PreparedStatement st = connection.prepareStatement(
						"INSERT INTO sheet (id_enrollee, id_department) values (?,?)",
						Statement.RETURN_GENERATED_KEYS);) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idDepartment);
			st.executeUpdate();

			try (ResultSet key = st.getGeneratedKeys();) {
				if (key.next())
					result = key.getInt(1);

			}
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return result;
	}

	@Override
	public Map<Department, List<Enrollee>> getSheet() {
		Map<Department, List<Enrollee>> result = new LinkedHashMap<Department, List<Enrollee>>();

		try (PreparedStatement sheetStatement = connection.prepareStatement("SELECT id_department, id_enrollee FROM sheet; ");) {

			result = getSheetMap(sheetStatement.executeQuery());

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return result;
	}
	
	private Map<Department, List<Enrollee>> getSheetMap(ResultSet sheetRS) throws SQLException{
		Map<Department, List<Enrollee>> result = new LinkedHashMap<Department, List<Enrollee>>();
		
		JDBCDepartmentDao departmentDAO = new JDBCDepartmentDao(connection);
		List<Department> departmentList = departmentDAO.findAll();
		
		JDBCEnrolleeDao enrolleeDAO = new JDBCEnrolleeDao(connection);
		List<Enrollee> enrolleeList = enrolleeDAO.findAll();
		
		while (sheetRS.next()) {
			
			Department department = getDepartmentFromListById(departmentList, sheetRS.getLong(1));
			Enrollee enrollee = getSubjectFromListById(enrolleeList, sheetRS.getLong(2));
			
			if(result.containsKey(department)){
				List<Enrollee> listConcreteEnrollee = result.get(department);
				listConcreteEnrollee.add(enrollee);
			} else {
				List<Enrollee> listConcreteEnrollee = new ArrayList<>();
				listConcreteEnrollee.add(enrollee);
				result.put(department, listConcreteEnrollee);
			}
		}
		return result;
	}
	
	private Department getDepartmentFromListById(List<Department> departmentList, long departmentId) {
		Department result;
        Optional<Department> departmentOptional = departmentList.stream().filter(d -> d.getId()==departmentId).findFirst();
        if (departmentOptional.isPresent()) {
            result = departmentOptional.get();
        } else {
            throw new IllegalStateException();
        }
        return result;
    }
	
	private Enrollee getSubjectFromListById(List<Enrollee> enrolleeList, long enrolleeId) {
		Enrollee result;
        Optional<Enrollee> enrolleeOptional = enrolleeList.stream().filter(d -> d.getId()==enrolleeId).findFirst();
        if (enrolleeOptional.isPresent()) {
            result = enrolleeOptional.get();
        } else {
            throw new IllegalStateException();
        }
        return result;
    }
	
	@Override
	public void deleteDepartment(long idDepartment) {
		try (PreparedStatement st = connection.prepareStatement("DELETE FROM sheet WHERE id_department = ?");) {

			st.setLong(1, idDepartment);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public void deleteEnrollee(long idEnrollee) {
		try (PreparedStatement st = connection.prepareStatement("DELETE FROM sheet WHERE id_enrollee  = ?");) {

			st.setLong(1, idEnrollee);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}

	}

	@Override
	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		try (PreparedStatement st = connection
						.prepareStatement("DELETE FROM sheet WHERE id_enrollee  = ? and id_department = ?");) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idDepartment);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

}
