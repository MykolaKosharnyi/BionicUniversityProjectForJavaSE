package model.dao;

import java.util.Properties;

import org.apache.log4j.Logger;

public abstract class DaoFactory {
	static Logger logger = Logger.getLogger(DaoFactory.class);
		public abstract EnrolleeDao createEnrolleeDao();
		public abstract CertificateDao createCertificateDao();
		public abstract SubjectDao createSubjectDao();
		public abstract DepartmentDao createDepartmentDao();
		public abstract SheetDao createSheetDao();
	    
	    public static DaoFactory getInstance(){
	        try {
	            Properties config = new Properties();
	            return (DaoFactory) Class.forName(config.getProperty("dao.factory", "dao.jdbc.JDBCDaoFactory")).newInstance();
	        } catch (Exception ex) {
	            logger.error("Exception: " + ex);
	            return null;
	        }
	    }
	}
