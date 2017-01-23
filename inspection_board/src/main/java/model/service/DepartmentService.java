package model.service;

import java.util.List;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entity.Department;

public class DepartmentService {
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	private DepartmentService(){}
	
	private static class Holder{
    	static final DepartmentService INSTANCE = new DepartmentService(); 
    }
    
    public static DepartmentService getInstance(){
    	return Holder.INSTANCE;
    }

	public long create(Department department) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			DepartmentDao dao = daoFactory.createDepartmentDao(connection);
			long result = dao.create(department);
			connection.commit();
			return result;
		}
	}

	public Department find(long id) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			DepartmentDao dao = daoFactory.createDepartmentDao(connection);
			Department result = dao.find(id);
			connection.commit();
			return result;
		}
	}

	public List<Department> findAll() {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			DepartmentDao dao = daoFactory.createDepartmentDao(connection);
			List<Department> result = dao.findAll();
			connection.commit();
			return result;
		}
	}

	public void update(Department t) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			DepartmentDao dao = daoFactory.createDepartmentDao(connection);
			dao.update(t);
			connection.commit();
		}
	}

	public void delete(long id) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			DepartmentDao dao = daoFactory.createDepartmentDao(connection);
			dao.delete(id);
			connection.commit();
		}
	}

}
