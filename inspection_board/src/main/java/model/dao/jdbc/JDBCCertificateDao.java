package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.dao.CertificateDao;
import model.dao.exception.DaoException;
import model.entity.Certificate;
import model.entity.Subject;

public class JDBCCertificateDao implements CertificateDao {
	private static final String INSERT_CERTIFICATE = "INSERT INTO certificate (id_subject, scope, id_enrollee) values (?,?,?)";
	private static final String SELECT_REPEAD_SUBJECT = "SELECT * FROM certificate WHERE id_enrollee = ? and id_subject = ?";
	private static final String SELECT_BY_ID_ENROLLEE = "SELECT * FROM certificate WHERE id_enrollee = ?";
	private static final String SELECT_ALL_SUBJECT = "SELECT * FROM subject";
	private static final String UPDATE_CERTIFICATE = "UPDATE certificate SET scope = ? WHERE id_enrollee = ? and id_subject = ? ";
	private static final String DELETE_BY_ENROLLE = "DELETE FROM certificate WHERE id_enrollee = ?";
	private static final String DELETE_BY_ENROLLE_AND_SYBJECT = "DELETE FROM certificate WHERE (id_enrollee = ? and id_subject = ?)";
	private static final String EXCEPTION_MSG_ADD_SUBJECT_TO_CERTIFICATE =
            "Exception during adding new subject to certicate, idEnrollee=%d, idSubject=%d, scope = %d";
	private static final String EXCEPTION_MSG_REPEAD_SUBJECT = 
			"Exception while finding repeated subject, idEnrollee=%d, idSubject=%d";
	private static final String EXCEPTION_MSG_FIND_BY_ID_ENROLLEE = 
			"Exception during finding certificate by id enrollee, idEnrollee=%d";
	private static final String EXCEPTION_MSG_UPDATE_CERTIFICATE = 
			"Exception during updating certifivate, idEnrollee=%d, idSubject=%d, valueOfSubject=%d";
	private static final String EXCEPTION_MSG_DELETE_FROM_CERTIFICATE_BY_ENROLLE = 
			"Exception during deleting all informaiton about enrolle from sertificate, idEnrollee=%d";
	private static final String EXCEPTION_MSG_DELETE_FROM_CERTIFICATE_BY_ENROLLE_SUBJECT = 
			"Exception during deleting subject from user certificate, idEnrollee=%d, inSubject=%d ";
	
	private Connection connection;

	public JDBCCertificateDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean add(long idEnrollee, long idSubject, int scope) {
		boolean result = !findRepeadSubject(idEnrollee, idSubject);
		if (result) {

			try (PreparedStatement st = connection.prepareStatement(INSERT_CERTIFICATE);) {

				st.setLong(1, idSubject);
				st.setInt(2, scope);
				st.setLong(3, idEnrollee);
				st.executeUpdate();

			} catch (SQLException e) {
				String message = String.format(EXCEPTION_MSG_ADD_SUBJECT_TO_CERTIFICATE, idEnrollee, idSubject, scope);
	            throw new DaoException(message, e);
			}
		}
		return result;
	}

	private boolean findRepeadSubject(long idEnrollee, long idSubject) {
		boolean result = false;
		try (PreparedStatement st = connection.prepareStatement(SELECT_REPEAD_SUBJECT)) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idSubject);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_REPEAD_SUBJECT, idEnrollee, idSubject);
            throw new DaoException(message, e);
		}
		return result;
	}

	@Override
	public Certificate find(long idEnrollee) {
		Certificate result = new Certificate();
		try (PreparedStatement statementCertificate = connection.prepareStatement(SELECT_BY_ID_ENROLLEE);
				PreparedStatement statementSubject = connection.prepareStatement(SELECT_ALL_SUBJECT)) {
			statementCertificate.setLong(1, idEnrollee);

			try (ResultSet rsCertificate = statementCertificate.executeQuery();
					ResultSet rsSubject = statementSubject.executeQuery()) {

				result = parseResultSets(rsCertificate, rsSubject);
			}
		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_FIND_BY_ID_ENROLLEE, idEnrollee);
            throw new DaoException(message, e);
		}
		return result;
	}

	/**
	 * Create Certificate from ResultSet rsCertificate and ResultSet rsSubject
	 * 
	 * @param rsCertificate
	 * @param rsSubject
	 * @return
	 * @throws SQLException
	 */
	private Certificate parseResultSets(ResultSet rsCertificate, ResultSet rsSubject) throws SQLException {
		Certificate result = new Certificate();
		Map<Subject, Integer> itemsWithEstimates = new LinkedHashMap<>();

		List<Subject> subjectList = new ArrayList<Subject>();
		while (rsSubject.next()) {
			subjectList.add(getSubjectFromResultSet(rsSubject));
		}

		while (rsCertificate.next()) {
			itemsWithEstimates.put(getSubjectFromListById(subjectList, rsCertificate.getLong(2)),
					rsCertificate.getInt(3));
		}

		result.setItemsWithEstimates(itemsWithEstimates);
		return result;
	}

	/**
	 * Create Subject from ResultSet
	 * 
	 * @param rsSubject
	 * @return
	 * @throws SQLException
	 */
	private Subject getSubjectFromResultSet(ResultSet rsSubject) throws SQLException {
		return new Subject(rsSubject.getLong(1), rsSubject.getString(2));
	}

	private Subject getSubjectFromListById(List<Subject> subjectList, long subjectId) {
		Subject result;
		Optional<Subject> orderOptional = subjectList.stream().filter(o -> o.getId() == subjectId).findFirst();
		if (orderOptional.isPresent()) {
			result = orderOptional.get();
		} else {
			throw new IllegalStateException();
		}
		return result;
	}

	@Override
	public void update(long idEnrollee, Subject subject, int valueOfSubject) {
		try (PreparedStatement st = connection.prepareStatement(UPDATE_CERTIFICATE);) {

			st.setInt(1, valueOfSubject);
			st.setLong(2, idEnrollee);
			st.setLong(3, subject.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_UPDATE_CERTIFICATE, idEnrollee, subject.getId(), valueOfSubject);
            throw new DaoException(message, e);
		}
	}

	@Override
	public void delete(long idEnrollee) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ENROLLE);) {

			st.setLong(1, idEnrollee);
			st.executeUpdate();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_DELETE_FROM_CERTIFICATE_BY_ENROLLE, idEnrollee);
            throw new DaoException(message, e);
		}
	}

	@Override
	public void delete(long idEnrollee, long idSubject) {
		try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ENROLLE_AND_SYBJECT);) {

			st.setLong(1, idEnrollee);
			st.setLong(2, idSubject);
			st.executeUpdate();

		} catch (SQLException e) {
			String message = String.format(EXCEPTION_MSG_DELETE_FROM_CERTIFICATE_BY_ENROLLE_SUBJECT, idEnrollee, idSubject);
            throw new DaoException(message, e);
		}
	}

}
