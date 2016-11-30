package model.service;

import java.util.List;

import model.entity.Subject;

interface SubjectService {
	long create( Subject subject );
	Subject find( long id );
	List<Subject> findAll();
	void update(Subject subject);
	void delete(long subject);
}
