package model.dao;

import java.util.List;

import model.entity.Enrollee;

public interface EnrolleeDao {
    void create(   Enrollee   enrollee );
    Enrollee find( int id );
    List<Enrollee> findAll();
    void update(Enrollee enrollee);
    void delete (int id);
}