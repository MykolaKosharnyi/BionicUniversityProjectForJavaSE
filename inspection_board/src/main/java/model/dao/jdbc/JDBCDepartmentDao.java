package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.DepartmentDao;
import model.entity.Department;
import model.entity.Subject;

public class JDBCDepartmentDao implements DepartmentDao {

	@Override
	public void create(Department department) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				
				//for adding new Department
				try {
					st = cn.prepareStatement(
							"INSERT INTO department (name, max_enrollee) values (?,?)",
							Statement.RETURN_GENERATED_KEYS);
					st.setString( 1, department.getNameDepartment());
					st.setInt( 2, department.getMaxAmountStudent());
					st.executeUpdate();

					ResultSet key = null;
					try {
						key = st.getGeneratedKeys();
						long userId = 0;
						if (key.next()) {
							userId = key.getLong(1);
						}
						department.setId(userId);
					} finally {
						if (key != null)
							key.close();
						key = null;
					}
				} finally {
					if (st != null)
						st.close();
					st = null;
				}
				
				//for coherenting Department and Subject
				for(Subject subject : department.getNecessaryItems()){
					try {
						st = cn.prepareStatement(
								"INSERT INTO department_subject (id_department, id_subject) values (?,?)");
						st.setLong( 1, department.getId());
						st.setLong( 2, subject.getId());
						st.executeUpdate();

					} finally {
						if (st != null)
							st.close();
						st = null;
					}
				}
				
			} finally {
				if (cn != null)
					cn.close();
				cn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Department find(long id) {
		Department department = null;
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM department WHERE id_department = ?");
					st.setLong(1, id);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						if (rs.next()) {
							department = new Department(rs.getLong(1), rs.getString(2), rs.getInt(3));
							
							department.setNecessaryItems(getNecessaryItems(id));
							
						}
					} finally {
						if (rs != null)
							rs.close();
						rs = null;
					}
				} finally {
					if (st != null)
						st.close();
					st = null;
				}
			} finally {
				if (cn != null)
					cn.close();
				cn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}
	
	private List<Subject> getNecessaryItems(long id){
		List<Subject> subjects = new ArrayList<>();
		 
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				Statement st = null;
				try {
					st = cn.createStatement();
					ResultSet rs = null;
					try {
						rs = st.executeQuery("SELECT DISTINCT id_department FROM department_subject; ");

						while (rs.next()) {
							subjects.add(new JDBCDaoFactory().createSubjectDao().find(rs.getLong(1)));
				        }

					} finally {
						if (rs != null)
							rs.close();
							rs=null;
					}
				} finally {
					if (st != null)
						st.close();
						st=null;
				}
			} finally {
				if (cn != null)
					cn.close();
					cn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return subjects;
	}

	@Override
	public List<Department> findAll() {
		List<Department> department = new ArrayList<Department>();
		 
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				Statement st = null;
				try {
					st = cn.createStatement();
					ResultSet rs = null;
					try {
						rs = st.executeQuery("SELECT * FROM department ");
						Department current = null;
						 while (rs.next()) {
							 current = new Department(rs.getLong(1), rs.getString(2), rs.getInt(3));
							 current.setNecessaryItems(getNecessaryItems(rs.getLong(1)));
				            department.add(current);
				    	}
					} finally {
						if (rs != null)
							rs.close();
							rs=null;
					}
				} finally {
					if (st != null)
						st.close();
						st=null;
				}
			} finally {
				if (cn != null)
					cn.close();
					cn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return department;
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
