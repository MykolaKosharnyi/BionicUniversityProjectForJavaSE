package model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.dao.CertificateDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.UserDao;
import model.dao.SheetDao;
import model.dao.SubjectDao;

public class JDBCDaoFactory extends DaoFactory {

	private DataSource dataSource;

	public JDBCDaoFactory() {
		try {
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/MySQLDB");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public DaoConnection getConnection() {
		try {
			return new JdbcDaoConnection(dataSource.getConnection());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserDao createEnrolleeDao(DaoConnection connection) {
		return new JDBCUserDao(getSqlConnection(connection));
	}

	@Override
	public CertificateDao createCertificateDao(DaoConnection connection) {
		return new JDBCCertificateDao(getSqlConnection(connection));
	}

	@Override
	public SubjectDao createSubjectDao(DaoConnection connection) {
		return new JDBCSubjectDao(getSqlConnection(connection));
	}

	@Override
	public DepartmentDao createDepartmentDao(DaoConnection connection) {
		return new JDBCDepartmentDao(getSqlConnection(connection));
	}

	@Override
	public SheetDao createSheetDao(DaoConnection connection) {
		return new JDBCSheetDao(getSqlConnection(connection));
	}

	private Connection getSqlConnection(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		return jdbcConnection.getConnection();
	}

}
