package model.service;

import java.util.List;

import model.dao.SubjectDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Subject;

public class SubjectService{
	SubjectDao subjectDao = new JDBCDaoFactory().createSubjectDao();

	public long create(Subject subject) {
		return subjectDao.create(subject);
	}

	public Subject find(long id) {
		return subjectDao.find(id);
	}

	public List<Subject> findAll() {
		return subjectDao.findAll();
	}

	public void update(Subject subject) {
		subjectDao.update(subject);
	}

	public void delete(long subject) {
		subjectDao.delete(subject);
	}

}
