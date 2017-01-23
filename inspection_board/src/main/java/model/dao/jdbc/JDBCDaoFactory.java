package model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.dao.CertificateDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.EnrolleeDao;
import model.dao.SheetDao;
import model.dao.SubjectDao;

public class JDBCDaoFactory extends DaoFactory{
	
	private DataSource dataSource;
	
    public JDBCDaoFactory() {
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/MySQLDB");   
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

	@Override
	public DaoConnection getConnection() {
			try {
				return new JdbcDaoConnection( dataSource.getConnection() );
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	@Override
	public EnrolleeDao createEnrolleeDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection(); 	
		return new JDBCEnrolleeDao(sqlConnection);
	}

	@Override
	public CertificateDao createCertificateDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection(); 
		return new JDBCCertificateDao(sqlConnection);
	}

	@Override
	public SubjectDao createSubjectDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection(); 
		return new JDBCSubjectDao(sqlConnection);
	}

	@Override
	public DepartmentDao createDepartmentDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection(); 
		return new JDBCDepartmentDao(sqlConnection);
	}

	@Override
	public SheetDao createSheetDao(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection(); 
		return new JDBCSheetDao(sqlConnection);
	}

}
