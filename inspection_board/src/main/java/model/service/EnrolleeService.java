package model.service;

import java.util.List;

import model.entity.Enrollee;

interface EnrolleeService {
	long create(Enrollee enrollee);

	Enrollee find(long id);

	List<Enrollee> findAll();

	void update(Enrollee enrollee);

	void delete(long id);

	Enrollee findByEmail(String email);

	boolean checkLogin(String email, String password);
}
