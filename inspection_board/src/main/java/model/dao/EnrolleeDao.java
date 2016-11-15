package model.dao;

import java.util.List;

import model.entity.Enrollee;

public interface EnrolleeDao {
    long create( Enrollee enrollee );
    Enrollee find( long id );
    Enrollee findByEmail( String email );
    List<Enrollee> findAll();
    void update(Enrollee enrollee);
    void delete (long id);
    boolean checkLogin(String email, String password);
}