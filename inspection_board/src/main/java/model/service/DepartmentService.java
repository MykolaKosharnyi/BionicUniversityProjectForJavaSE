package model.service;

import java.util.List;
import java.util.Optional;

import model.entity.Department;

public interface DepartmentService {
	long create(Department department);
	Optional<Department> find(long id);
	List<Department> findAll();
	void update(Department t);
	void delete(long id);
}

