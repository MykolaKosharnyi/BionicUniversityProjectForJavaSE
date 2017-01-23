package model.service;

import java.util.List;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.EnrolleeDao;
import model.entity.Enrollee;

public class EnrolleeService{
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	private EnrolleeService(){}
	
	private static class Holder{
    	static final EnrolleeService INSTANCE = new EnrolleeService(); 
    }
    
    public static EnrolleeService getInstance(){
    	return Holder.INSTANCE;
    }

	public long create(Enrollee enrollee) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			EnrolleeDao dao = daoFactory.createEnrolleeDao(connection);
			long result = dao.create(enrollee);
			connection.commit();
			return result;
		}
	}

	public Enrollee find(long id) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			EnrolleeDao dao = daoFactory.createEnrolleeDao(connection);
			Enrollee result = dao.find(id);
			connection.commit();
			return result;
		}
	}

	public List<Enrollee> findAll() {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			EnrolleeDao dao = daoFactory.createEnrolleeDao(connection);
			List<Enrollee> result = dao.findAll();
			connection.commit();
			return result;
		}
	}

	public void update(Enrollee enrollee) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			EnrolleeDao dao = daoFactory.createEnrolleeDao(connection);
			dao.update(enrollee);
			connection.commit();
		}
	}

	public void delete(long id) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			EnrolleeDao dao = daoFactory.createEnrolleeDao(connection);
			dao.delete(id);
			connection.commit();
		}
	}

	public Enrollee findByEmail(String email) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			EnrolleeDao dao = daoFactory.createEnrolleeDao(connection);
			Enrollee result = dao.findByEmail(email);
			connection.commit();
			return result;
		}
	}

	public boolean checkLogin(String email, String password) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			EnrolleeDao dao = daoFactory.createEnrolleeDao(connection);
			boolean result = dao.checkLogin(email, password);
			connection.commit();
			return result;
		}
	}

}
