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
import model.service.SubjectService;

public class JDBCDepartmentDao implements DepartmentDao {
	
	static Logger logger = Logger.getLogger(JDBCDepartmentDao.class);
	
	private Connection connection;
	
	JDBCDepartmentDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long create(Department department) {
		long result = 0;
			try(PreparedStatement st = connection.prepareStatement("INSERT INTO department (name, max_enrollee) values (?,?)",
					Statement.RETURN_GENERATED_KEYS)){
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
				try(PreparedStatement st = connection.prepareStatement(
						"INSERT INTO department_subject (id_department, id_subject) values (?,?)")) {
					
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

		try (PreparedStatement st = connection.prepareStatement("SELECT * FROM department WHERE id_department = ?");) {

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
		List<Subject> subjects = new ArrayList<>();

		try (PreparedStatement st = connection
						.prepareStatement("SELECT DISTINCT id_subject FROM department_subject WHERE id_department = ?; ");) {
			st.setLong( 1, id);
			
			ResultSet rs = st.executeQuery();
		
			while (rs.next()) {
				subjects.add(SubjectService.getInstance().find(rs.getLong(1)));
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return subjects;
	}

	@Override
	public List<Department> findAll() {
		List<Department> department = new ArrayList<Department>();

		try(Statement st = connection.createStatement();) {

			ResultSet rs = st.executeQuery("SELECT * FROM department ");
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

			try (PreparedStatement st = connection
					.prepareStatement("UPDATE department SET name = ?, max_enrollee = ? WHERE id_department = ? ")) {
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
				try (PreparedStatement st = connection
						.prepareStatement("DELETE FROM department_subject WHERE id_department = ?")) {
					st.setLong(1, department.getId());
					st.executeUpdate();
				} catch (SQLException e) {
					logger.error(e.getStackTrace());
				}

				// add new subject of this department
				for (Subject subject : department.getNecessaryItems()) {
					try (PreparedStatement st = connection.prepareStatement(
							"INSERT INTO department_subject (id_department, id_subject) values (?,?)")) {

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
		
			try(PreparedStatement st = connection.prepareStatement("DELETE FROM department WHERE id_department = ?")){
				st.setLong(1, id);
				st.executeUpdate();
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
			}
			
			try(PreparedStatement st = connection.prepareStatement("DELETE FROM department_subject WHERE id_department = ?")){
				st.setLong(1, id);
				st.executeUpdate();
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
			}	
			
	}

}
