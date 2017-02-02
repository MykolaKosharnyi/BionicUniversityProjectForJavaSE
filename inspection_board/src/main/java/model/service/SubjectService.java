package model.service;

import java.util.List;
import java.util.Optional;

import model.entity.Subject;

public interface SubjectService {
	long create(Subject subject);
	Optional<Subject> find(long id);
	List<Subject> findAll();
	void update(Subject subject);
	void delete(long subject);
}

