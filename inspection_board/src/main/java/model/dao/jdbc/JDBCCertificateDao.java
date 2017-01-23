package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import model.dao.CertificateDao;
import model.entity.Certificate;
import model.entity.Subject;
import model.service.SubjectService;

public class JDBCCertificateDao implements CertificateDao {
	
	static Logger logger = Logger.getLogger(JDBCCertificateDao.class);
	
	private Connection connection;
	
	JDBCCertificateDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean add(long idEnrollee, long idSubject, int scope) {
		boolean result = !findRepeadSubject(idEnrollee, idSubject);
		if (result) {

			try (PreparedStatement st = connection
					.prepareStatement("INSERT INTO certificate (id_subject, scope, id_enrollee) values (?,?,?)");) {

				st.setLong(1, idSubject);
				st.setInt(2, scope);
				st.setLong(3, idEnrollee);
				st.executeUpdate();

			} catch (SQLException e) {
				logger.error(e.getStackTrace());
			}
		}
		return result;
	}

	private boolean findRepeadSubject(long idEnrollee, long idSubject) {
		boolean result = false;
		try (PreparedStatement st = connection
						.prepareStatement("SELECT * FROM certificate WHERE id_enrollee = ? and id_subject = ?");) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idSubject);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return result;
	}
	
	@Override
	public Certificate find(long idEnrollee) {
		Certificate certificate = new Certificate();
		try (PreparedStatement st = connection.prepareStatement("SELECT * FROM certificate WHERE id_enrollee = ?");) {

			st.setLong(1, idEnrollee);
			ResultSet rs = st.executeQuery();
			Map<Subject, Integer> itemsWithEstimates = new HashMap<>();
			while (rs.next()) {
				itemsWithEstimates.put(SubjectService.getInstance().find(rs.getLong(2)), rs.getInt(3));
			}
			certificate.setItemsWithEstimates(itemsWithEstimates);

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
		return certificate;
	}

	@Override
	public void update(long idEnrollee, Subject subject, int valueOfSubject) {
		try (PreparedStatement st = connection.prepareStatement(
						"UPDATE certificate SET scope = ? WHERE id_enrollee = ? and id_subject = ? ");) {

			st.setInt(1, valueOfSubject);
			st.setLong(2, idEnrollee);
			st.setLong(3, subject.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public void delete(long idEnrollee) {
		try (PreparedStatement st = connection.prepareStatement("DELETE FROM certificate WHERE id_enrollee = ?");) {

			st.setLong(1, idEnrollee);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

	@Override
	public void delete(long idEnrollee, long idSubject) {
		try (PreparedStatement st = connection
						.prepareStatement("DELETE FROM certificate WHERE (id_enrollee = ? and id_subject = ?)");) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idSubject);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		}
	}

}
