package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.SheetDao;
import model.entity.Department;
import model.entity.Enrollee;
import model.entity.Sheet;

public class JDBCSheetDao implements SheetDao {

	@Override
	public void add(long idEnrollee, long idDepartment) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement(
							"INSERT INTO sheet (id_enrollee, id_department) values (?,?)");
					st.setLong( 1, idEnrollee);
					st.setLong( 2, idDepartment);
					st.executeUpdate();

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

	}

	@Override
	public Sheet getSheet() {
		Sheet sheet = Sheet.getInstance();
		 
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				Statement st = null;
				try {
					st = cn.createStatement();
					ResultSet rs = null;
					try {
						rs = st.executeQuery("SELECT DISTINCT id_department FROM sheet; ");
						Map<Department, List<Enrollee>> sheetMap = new HashMap<Department, List<Enrollee>>();
						while (rs.next()) {
							 sheetMap.put(new JDBCDaoFactory().createDepartmentDao().find(rs.getLong(1)), getEnrolleeByDepartmentID(rs.getLong(1)));
				            }

						sheet.setSheet(sheetMap);
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
        return sheet;
	}

	private List<Enrollee> getEnrolleeByDepartmentID(long id){
		List<Enrollee> enrollee = new ArrayList<Enrollee>();
		 
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT id_enrollee FROM sheet where id_department = ? ");
					st.setLong(1, id);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						
						 while (rs.next()) {
				                enrollee.add( new JDBCDaoFactory().createEnrolleeDao().find(rs.getLong(1)) );
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
        return enrollee;
	}
	
	@Override
	public void deleteDepartment(long idDepartment) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM sheet WHERE id_department = ?");
					st.setLong(1, idDepartment);
		            st.executeUpdate();

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

	}

	@Override
	public void deleteEnrollee(long idEnrollee) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM sheet WHERE id_enrollee  = ?");
					st.setLong(1, idEnrollee);
		            st.executeUpdate();

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

	}

	@Override
	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM sheet WHERE id_enrollee  = ? and id_department = ?");
					st.setLong(1, idEnrollee);
					st.setLong(2, idDepartment);
		            st.executeUpdate();

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

	}

}
