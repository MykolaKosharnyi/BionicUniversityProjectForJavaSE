package model.service;

import java.util.List;

import model.dao.SubjectDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Subject;

public class SubjectServiceImpl implements SubjectService {
	SubjectDao subjectDao = new JDBCDaoFactory().createSubjectDao();
	
	@Override
	public long create(Subject subject) {
		return subjectDao.create(subject);
	}

	@Override
	public Subject find(long id) {
		return subjectDao.find(id);
	}

	@Override
	public List<Subject> findAll() {
		return subjectDao.findAll();
	}

	@Override
	public void update(Subject subject) {
		subjectDao.update(subject);
	}

	@Override
	public void delete(long subject) {
		subjectDao.delete(subject);
	}

}
