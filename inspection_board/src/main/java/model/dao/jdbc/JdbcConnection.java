package model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JdbcConnection {
	static Logger logger = Logger.getLogger(JdbcConnection.class);
    private JdbcConnection(){}
    
    private static JdbcConnection instance;
    
    public static JdbcConnection getInstance() {
		if (null == instance) {
			synchronized (JdbcConnection.class) {
				if (null == instance) {
					instance = new JdbcConnection();
					logger.info("create new JdbcConnection");
				}
			}
		}
		logger.info("return JdbcConnection");
		return instance;
	}
    
    public Connection getConnection(){
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQLDB");
        	logger.info("Return connection.");
            return ds.getConnection();
        } catch (NamingException ex) {
        	logger.error("NamingException: " + ex);
        } catch( SQLException exx){
        	logger.error("SQLException: " + exx);
        }
        return null;
    }
}
