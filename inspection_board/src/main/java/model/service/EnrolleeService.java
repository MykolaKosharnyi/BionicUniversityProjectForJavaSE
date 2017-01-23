package model.service;

import java.util.List;

import model.dao.EnrolleeDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Enrollee;

public class EnrolleeService{
	EnrolleeDao enrolleeDao = new JDBCDaoFactory().createEnrolleeDao();

	public long create(Enrollee enrollee) {
		return enrolleeDao.create(enrollee);
	}

	public Enrollee find(long id) {
		return enrolleeDao.find(id);
	}

	public List<Enrollee> findAll() {
		return enrolleeDao.findAll();
	}

	public void update(Enrollee enrollee) {
		enrolleeDao.update(enrollee);
	}

	public void delete(long id) {
		enrolleeDao.delete(id);
	}

	public Enrollee findByEmail(String email) {
		return enrolleeDao.findByEmail(email);
	}

	public boolean checkLogin(String email, String password) {
		return enrolleeDao.checkLogin(email, password);
	}

}
