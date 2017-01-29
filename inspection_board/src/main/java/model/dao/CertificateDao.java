package model.dao;

import model.entity.Certificate;
import model.entity.Subject;

public interface CertificateDao {
	boolean add(long idEnrollee, long idSubject, int scope);

	Certificate find(long idEnrollee);

	void update(long idEnrollee, Subject subject, int valueOfSubject);

	void delete(long idEnrollee);

	void delete(long idEnrollee, long idSubject);
}
