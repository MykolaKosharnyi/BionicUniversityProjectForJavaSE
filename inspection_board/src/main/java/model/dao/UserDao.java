package model.dao;


import java.util.Optional;

import model.entity.User;

public interface UserDao extends GenericDAO<User> {
    
    Optional<User> findByEmail( String email );    
    boolean checkLogin(String email, String password);
    
}