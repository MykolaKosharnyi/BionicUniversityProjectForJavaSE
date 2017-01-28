package model.dao;

import java.util.Properties;

import org.apache.log4j.Logger;

public abstract class DaoFactory {
	static Logger logger = Logger.getLogger(DaoFactory.class);
	
	public abstract DaoConnection getConnection();

	public abstract UserDao createEnrolleeDao(DaoConnection connection);
	public abstract CertificateDao createCertificateDao(DaoConnection connection);
	public abstract SubjectDao createSubjectDao(DaoConnection connection);
	public abstract DepartmentDao createDepartmentDao(DaoConnection connection);
	public abstract SheetDao createSheetDao(DaoConnection connection);

	public static DaoFactory getInstance() {
		try {
			Properties config = new Properties();
			return (DaoFactory) Class.forName(config.getProperty("dao.factory", "model.dao.jdbc.JDBCDaoFactory"))
					.newInstance();
		} catch (Exception ex) {
			logger.error("Exception: " + ex);
			return null;
		}
	}
}
