package model.service.impl;

import model.dao.CertificateDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.entity.Certificate;
import model.entity.Subject;
import model.service.CertificateService;

public class CertificateServiceImpl implements CertificateService{

	private DaoFactory daoFactory = DaoFactory.getInstance();

	private CertificateServiceImpl() {
	}

	private static class Holder {
		static final CertificateServiceImpl INSTANCE = new CertificateServiceImpl();
	}

	public static CertificateServiceImpl getInstance() {
		return Holder.INSTANCE;
	}

	public boolean addSubject(long idEnrollee, long idSubject, int scope) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			CertificateDao dao = daoFactory.createCertificateDao(connection);
			boolean result = dao.add(idEnrollee, idSubject, scope);
			connection.commit();
			return result;
		}
	}

	public Certificate find(long idEnrollee) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			CertificateDao dao = daoFactory.createCertificateDao(connection);
			return dao.find(idEnrollee);
		}
	}

	public void updateSubject(long idEnrollee, Subject subject, int valueOfSubject) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			CertificateDao dao = daoFactory.createCertificateDao(connection);
			dao.update(idEnrollee, subject, valueOfSubject);
			connection.commit();
		}
	}

	public void deleteEnrolleeCertificate(long idEnrollee) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			CertificateDao dao = daoFactory.createCertificateDao(connection);
			dao.delete(idEnrollee);
			connection.commit();
		}
	}

	public void deleteSubject(long idEnrollee, long idSubject) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			CertificateDao dao = daoFactory.createCertificateDao(connection);
			dao.delete(idEnrollee, idSubject);
			connection.commit();
		}
	}

}
