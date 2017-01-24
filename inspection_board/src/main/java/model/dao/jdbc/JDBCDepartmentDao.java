package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.dao.DepartmentDao;
import model.entity.Department;
import model.entity.Subject;

public class JDBCDepartmentDao implements DepartmentDao {
	
	static Logger logger = Logger.getLogger(JDBCDepartmentDao.class);
	
	private static final String INSERT_INTO_DEPARTMENT = "INSERT INTO department (name, max_enrollee) values (?,?)";
	private static final String INSERT_INTO_DEPARTMENT_SUBJECT = "INSERT INTO department_subject (id_department, id_subject) values (?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM department WHERE id_department = ?";
	private static final String SELECT_SUBJECTS = "SELECT subject.id_subject AS id_subject, subject.name AS name"
			+ " FROM department_subject"
			+ " INNER JOIN subject "
			+ " ON department_subject.id_subject=subject.id_subject "
			+ " WHERE id_department = ?; ";
	private static final String SELECT_ALL_DEPARTMENTS = "SELECT * FROM department";
	private static final String UPDATE_ALL_DEPARTMENT = "UPDATE department SET name = ?, max_enrollee = ? WHERE id_department = ?";
	private static final String DELETE_FROM_DEPARTMENT_SUBJECT = "DELETE FROM department_subject WHERE id_department = ?";
	private static final String DELETE_FROM_DEPARTMENT = "DELETE FROM department WHERE id_department = ?";
	
	private Connection connection;
	
	JDBCDepartmentDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long create(Department department) {
		long result = 0;
			try(PreparedStatement st = connection.prepareStatement(INSERT_INTO_DEPARTMENT, Statement.RETURN_GENERATED_KEYS)){
				st.setString( 1, department.getNameDepartment());
				st.setInt( 2, department.getMaxAmountStudent());
				st.executeUpdate();
				
				ResultSet key = st.getGeneratedKeys();
				if (key.next()) {
					result = key.getLong(1);
				}
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
			}
			
			//for subject with contain department
			for(Subject subject : department.getNecessaryItems()){
				try(PreparedStatement st = connection.prepareStatement(INSERT_INTO_DEPARTMENT_SUBJECT)) {
					
					st.setLong( 1, department.getId());
					st.setLong( 2, subject.getId());
					st.executeUpdate();
				} catch (SQLException e) {
					logger.error(e.getStackTrace());
				} 
			}
		
		return result;
	}

	@Override
	public Department find(long id) {
		Department department = null;

		try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID);) {

			st.setLong(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				department = new Department(rs.getLong(1), rs.getString(2), rs.getInt(3));

				department.setNecessaryItems(getNecessaryItems(id));
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return department;
	}
	
	private List<Subject> getNecessaryItems(long id) {
		List<Subject> result = new ArrayList<>();

		try (PreparedStatement statementSubject = connection.prepareStatement(SELECT_SUBJECTS);) {
			statementSubject.setLong(1, id);
			
			ResultSet rs = statementSubject.executeQuery();
		
			while (rs.next()) {
				result.add(new Subject(rs.getLong(1), rs.getString(2)));				
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return result;
	}

	@Override
	public List<Department> findAll() {
		List<Department> department = new ArrayList<Department>();

		try(Statement st = connection.createStatement();) {

			ResultSet rs = st.executeQuery(SELECT_ALL_DEPARTMENTS);
			Department current = null;
			while (rs.next()) {
				current = new Department(rs.getLong(1), rs.getString(2), rs.getInt(3));
				current.setNecessaryItems(getNecessaryItems(rs.getLong(1)));
				department.add(current);
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return department;
	}

	@Override
	public void update(Department department) {

			try (PreparedStatement st = connection.prepareStatement(UPDATE_ALL_DEPARTMENT)) {
				st.setString(1, department.getNameDepartment());
				st.setInt(2, department.getMaxAmountStudent());
				st.setLong(3, department.getId());

				st.executeUpdate();
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
			}

			//if changed subject in this department
			if (department.getNecessaryItems().retainAll(getNecessaryItems(department.getId()))) {

				// first delete old subject of this department
				try (PreparedStatement st = connection.prepareStatement(DELETE_FROM_DEPARTMENT_SUBJECT)) {
					st.setLong(1, department.getId());
					st.executeUpdate();
				} catch (SQLException e) {
					logger.error(e.getStackTrace());
				}

				// add new subject of this department
				for (Subject subject : department.getNecessaryItems()) {
					try (PreparedStatement st = connection.prepareStatement(INSERT_INTO_DEPARTMENT_SUBJECT)) {

						st.setLong(1, department.getId());
						st.setLong(2, subject.getId());
						st.executeUpdate();
					} catch (SQLException e) {
						logger.error(e.getStackTrace());
					}
				}
			}
	}

	@Override
	public void delete(long id) {
		
			try(PreparedStatement st = connection.prepareStatement(DELETE_FROM_DEPARTMENT)){
				st.setLong(1, id);
				st.executeUpdate();
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
			}
			
			try(PreparedStatement st = connection.prepareStatement(DELETE_FROM_DEPARTMENT_SUBJECT)){
				st.setLong(1, id);
				st.executeUpdate();
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
			}	
			
	}

}
