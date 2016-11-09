package model.dao.jdbc;

import model.dao.DaoFactory;
import model.dao.EnrolleeDao;

public class JDBCDaoFactory extends DaoFactory{

	@Override
	public EnrolleeDao createEnrolleeDao() {
		return new JDBCEnrolleeDao();
	}

}
