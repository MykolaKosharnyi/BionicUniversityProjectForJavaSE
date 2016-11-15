package model.dao;

import java.util.List;

import model.entity.Department;

public interface DepartmentDao {
	void create( Department department );
	Department find( long id );
    List<Department> findAll();
    void update(Department department);
    void delete (long id);
}
