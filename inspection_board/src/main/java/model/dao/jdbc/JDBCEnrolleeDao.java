package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.EnrolleeDao;
import model.entity.Enrollee;

public class JDBCEnrolleeDao implements EnrolleeDao {

	@Override
	public void create(Enrollee enrollee) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement(
							"INSERT INTO enrollee (id) values (?)",
							Statement.RETURN_GENERATED_KEYS);
					st.setLong( 1, enrollee.getId());
					st.executeUpdate();

					ResultSet key = null;
					try {
						key = st.getGeneratedKeys();
						int userId = 0;
						if (key.next()) {
							userId = key.getInt(1);
						}
						enrollee.setId(userId);
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
	public Enrollee find(int id) {
		Enrollee enrollee = null;
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM enrollee WHERE ID = ?");
					st.setInt(1, id);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						if (rs.next()) {
//							enrollee = new Enrollee(rs.getInt(1), rs.getInt(2));
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
		return enrollee;
	}

	@Override
	public List<Enrollee> findAll() {
		List<Enrollee> enrollee = new ArrayList<Enrollee>();
		 
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				Statement st = null;
				try {
					st = cn.createStatement();
					ResultSet rs = null;
					try {
						rs = st.executeQuery("SELECT * FROM enrollee ");
						 while (rs.next()) {
//				                enrollee.add(new Enrollee(rs.getInt(1) , rs.getInt(2)));
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
	public void update(Enrollee enrollee) {
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
// ??????			st = cn.prepareStatement("UPDATE enrollee SET (id_employee = ?) WHERE id = ?");
					st.setLong(1, enrollee.getId());
//					st.setInt(2, id);
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
	public void delete(int id) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM enrollee WHERE id = ?");
					st.setInt(1, id);
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
