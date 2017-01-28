package model.dao;


import model.entity.User;

public interface UserDao extends GenericDAO<User> {
    
    User findByEmail( String email );    
    boolean checkLogin(String email, String password);
    
}