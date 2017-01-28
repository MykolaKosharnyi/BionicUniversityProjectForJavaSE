package model.service;

import java.util.List;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

public class UserService{
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	private UserService(){}
	
	private static class Holder{
    	static final UserService INSTANCE = new UserService(); 
    }
    
    public static UserService getInstance(){
    	return Holder.INSTANCE;
    }

	public long create(User enrollee) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			long result = dao.create(enrollee);
			connection.commit();
			return result;
		}
	}

	public User find(long id) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			User result = dao.find(id);
			connection.commit();
			return result;
		}
	}

	public List<User> findAll() {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			List<User> result = dao.findAll();
			connection.commit();
			return result;
		}
	}

	public void update(User enrollee) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			dao.update(enrollee);
			connection.commit();
		}
	}

	public void delete(long id) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			dao.delete(id);
			connection.commit();
		}
	}

	public User findByEmail(String email) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			User result = dao.findByEmail(email);
			connection.commit();
			return result;
		}
	}

	public boolean checkLogin(String email, String password) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			boolean result = dao.checkLogin(email, password);
			connection.commit();
			return result;
		}
	}

}
