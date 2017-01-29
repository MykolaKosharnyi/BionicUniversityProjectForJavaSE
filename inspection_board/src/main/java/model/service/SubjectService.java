package model.service;

import java.util.List;
import java.util.Optional;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.SubjectDao;
import model.entity.Subject;

public class SubjectService{
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	private SubjectService(){}
	
	private static class Holder{
    	static final SubjectService INSTANCE = new SubjectService(); 
    }
    
    public static SubjectService getInstance(){
    	return Holder.INSTANCE;
    }

	public long create(Subject subject) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			SubjectDao dao = daoFactory.createSubjectDao(connection);
			long result = dao.create(subject);
			connection.commit();
			return result;
		}
	}

	public Optional<Subject> find(long id) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			SubjectDao dao = daoFactory.createSubjectDao(connection);
			return dao.find(id);
		}
	}

	public List<Subject> findAll() {
		try( DaoConnection connection = daoFactory.getConnection() ){
			SubjectDao dao = daoFactory.createSubjectDao(connection);
			return dao.findAll();
		}
	}

	public void update(Subject subject) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			SubjectDao dao = daoFactory.createSubjectDao(connection);
			dao.update(subject);
			connection.commit();
		}
	}

	public void delete(long subject) {
		//also need realization to delete from DEPARTMENT_SUBJECT(delete row) and CERTIFICATE(delete row)
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			SubjectDao dao = daoFactory.createSubjectDao(connection);
			dao.delete(subject);
			connection.commit();
		}
	}

}
