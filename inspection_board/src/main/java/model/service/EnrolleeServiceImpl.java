package model.service;

import java.util.List;

import model.dao.EnrolleeDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Enrollee;

public class EnrolleeServiceImpl implements EnrolleeService {
	EnrolleeDao enrolleeDao = new JDBCDaoFactory().createEnrolleeDao();
	
	@Override
	public long create(Enrollee enrollee) {
		return enrolleeDao.create(enrollee);
	}

	@Override
	public Enrollee find(long id) {
		return enrolleeDao.find(id);
	}

	@Override
	public List<Enrollee> findAll() {
		return enrolleeDao.findAll();
	}

	@Override
	public void update(Enrollee enrollee) {
		enrolleeDao.update(enrollee);
	}

	@Override
	public void delete(long id) {
		enrolleeDao.delete(id);
	}

	@Override
	public Enrollee findByEmail(String email) {
		return enrolleeDao.findByEmail(email);
	}

	@Override
	public boolean checkLogin(String email, String password) {
		return enrolleeDao.checkLogin(email, password);
	}

}
