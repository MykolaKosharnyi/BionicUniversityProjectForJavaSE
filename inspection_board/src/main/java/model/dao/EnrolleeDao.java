package model.dao;


import model.entity.Enrollee;

public interface EnrolleeDao extends GenericDAO<Enrollee> {
    
    Enrollee findByEmail( String email );    
    boolean checkLogin(String email, String password);
    
}