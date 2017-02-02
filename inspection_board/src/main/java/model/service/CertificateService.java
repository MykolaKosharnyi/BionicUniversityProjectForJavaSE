package model.service;

import model.entity.Certificate;
import model.entity.Subject;

public interface CertificateService {
	boolean addSubject(long idEnrollee, long idSubject, int scope);
	Certificate find(long idEnrollee);
	void updateSubject(long idEnrollee, Subject subject, int valueOfSubject);
	void deleteEnrolleeCertificate(long idEnrollee);
	void deleteSubject(long idEnrollee, long idSubject);
}
