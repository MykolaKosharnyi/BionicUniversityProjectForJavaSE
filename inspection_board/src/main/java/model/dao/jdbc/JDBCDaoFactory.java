package model.dao.jdbc;

import model.dao.CertificateDao;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.EnrolleeDao;
import model.dao.SheetDao;
import model.dao.SubjectDao;

public class JDBCDaoFactory extends DaoFactory{

	@Override
	public EnrolleeDao createEnrolleeDao() {
		return new JDBCEnrolleeDao();
	}

	@Override
	public CertificateDao createCertificateDao() {
		return new JDBCCertificateDao();
	}

	@Override
	public SubjectDao createSubjectDao() {
		return new JDBCSubjectDao();
	}

	@Override
	public DepartmentDao createDepartmentDao() {
		return new JDBCDepartmentDao();
	}

	@Override
	public SheetDao createSheetDao() {
		return new JDBCSheetDao();
	}

}
