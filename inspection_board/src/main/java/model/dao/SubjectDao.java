package model.dao;

import java.util.List;

import model.entity.Subject;

public interface SubjectDao {
	void create( Subject subject );
	Subject find( long id );
    List<Subject> findAll();
    void update(Subject subject);
    void delete (long id);
}
