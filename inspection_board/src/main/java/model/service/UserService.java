package model.service;

import java.util.List;
import java.util.Optional;

import model.entity.User;

public interface UserService {
	long create(User enrollee);
	Optional<User> find(long id);
	List<User> findAll();
	void update(User enrollee);
	void delete(long id);
	Optional<User> findByEmail(String email);
	boolean checkLogin(String email, String password);
}
