package model.service;

import model.dao.CertificateDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Certificate;
import model.entity.Subject;

public class CertificateService{
	CertificateDao certificate = new JDBCDaoFactory().createCertificateDao();

	public boolean addSubject(long idEnrollee, long idSubject, int scope) {
		return certificate.add(idEnrollee, idSubject, scope);
	}

	public Certificate find(long idEnrollee) {
		return certificate.find(idEnrollee);
	}

	public void updateSubject(long idEnrollee, Subject subject, int valueOfSubject) {
		certificate.update(idEnrollee, subject, valueOfSubject);
	}

	public void deleteEnrolleeCertificate(long idEnrollee) {
		certificate.delete(idEnrollee);
	}

	public void deleteSubject(long idEnrollee, long idSubject) {
		certificate.delete(idEnrollee, idSubject);
	}

}
