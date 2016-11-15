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
	public long create(Enrollee enrollee) {
		long result = 0;
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement(
							"INSERT INTO enrollee (firstName, secondName, email, phone, password) values (?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
					st.setString( 1, enrollee.getFirstName());
					st.setString( 2, enrollee.getSecondName());
					st.setString( 3, enrollee.getEmail());
					st.setString( 4, enrollee.getPhone());
					st.setString( 5, enrollee.getPassword());
					st.executeUpdate();

					ResultSet key = null;
					try {
						key = st.getGeneratedKeys();
						if (key.next()) {
							result = key.getInt(1);
						}
						enrollee.setId(result);
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
		return result;
	}

	@Override
	public Enrollee find(long id) {
		Enrollee enrollee = null;
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM enrollee WHERE ID = ?");
					st.setLong(1, id);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						if (rs.next()) {
							enrollee = new Enrollee(rs.getInt(1), rs.getString(2), rs.getString(3),
									rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
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
				                enrollee.add(new Enrollee(rs.getInt(1), rs.getString(2), rs.getString(3),
										rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7)));
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
					st = cn.prepareStatement("UPDATE enrollee SET firstName = ?, secondName = ?, email = ?, phone = ?, password = ? WHERE id = ? ");
					st.setString(1, enrollee.getFirstName());
					st.setString(2, enrollee.getSecondName());
					st.setString(3, enrollee.getEmail());
					st.setString(4, enrollee.getPhone());
					st.setString(5, enrollee.getPassword());
					st.setLong(6, enrollee.getId());
					
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
	public void delete(long id) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM enrollee WHERE id = ?");
					st.setLong(1, id);
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
	public boolean checkLogin(String email, String password) {
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM enrollee WHERE email = ? AND password = ?");
					st.setString(1, email);
					st.setString(2, password);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();

						return rs.next();
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
			return false;
		} 
	}

	@Override
	public Enrollee findByEmail(String email) {
		Enrollee enrollee = null;
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM enrollee WHERE email = ?");
					st.setString(1, email);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						if (rs.next()) {
							enrollee = new Enrollee(rs.getInt(1), rs.getString(2), rs.getString(3),
									rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
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

}
