package model.service;

import java.util.List;

import model.entity.Department;

interface DepartmentService {
	long create(Department department);

	Department find(long id);

	List<Department> findAll();

	void update(Department t);

	void delete(long id);
}
