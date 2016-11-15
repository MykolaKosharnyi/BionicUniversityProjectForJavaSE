package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import model.dao.CertificateDao;
import model.entity.Certificate;
import model.entity.Subject;

public class JDBCCertificateDao implements CertificateDao {

	@Override
	public void create(long idEnrollee, Certificate certificate) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				
				for(Map.Entry<Subject, Integer> entry : certificate.getItemsWithEstimates().entrySet()){
					
					try {
						st = cn.prepareStatement(
								"INSERT INTO certificate (id_subject, scope, id_enrollee) values (?,?,?)");
						st.setLong( 1, entry.getKey().getId());
						st.setInt( 2, entry.getValue());
						st.setLong( 3, idEnrollee);
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
	public Certificate find(long idEnrollee) {
		Certificate certificate = new Certificate();
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM certificate WHERE id_enrollee = ?");
					st.setLong(1, idEnrollee);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						Map<Subject, Integer> itemsWithEstimates = new TreeMap<>();
						while (rs.next()) {
							itemsWithEstimates.put(new JDBCDaoFactory().createSubjectDao().find(rs.getLong(2)), rs.getInt(3));
						}
						certificate.setItemsWithEstimates(itemsWithEstimates);
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
		return certificate;
	}

	@Override
	public void updateSubject(long idEnrollee, Subject subject, int valueOfSubject) {
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("UPDATE certificate SET scope = ? WHERE id_enrollee = ? and id_subject = ? ");
					st.setInt(1, valueOfSubject);
					st.setLong(2, idEnrollee);
					st.setLong(3, subject.getId());
					
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
	public void deleteCertificate(long idEnrollee) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM certificate WHERE id_enrollee = ?");
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
	public void deleteSubject(long idEnrollee, long idSubject) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM certificate WHERE id_enrollee = ? and id_subject = ?");
					st.setLong(1, idEnrollee);
					st.setLong(1, idSubject);
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
