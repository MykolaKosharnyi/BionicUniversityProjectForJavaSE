package model.dao;

import model.entity.Certificate;
import model.entity.Subject;

public interface CertificateDao {
	void create(long idEnrollee, Certificate certificate );
	Certificate find( long idEnrollee );
    void updateSubject(long idEnrollee, Subject subject, int valueOfSubject);
    void deleteCertificate (long idEnrollee);
    void deleteSubject (long idEnrollee, long idSubject);
}
