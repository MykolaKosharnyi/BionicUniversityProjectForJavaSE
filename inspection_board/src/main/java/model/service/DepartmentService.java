package model.service;

import java.util.List;

import model.dao.DepartmentDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Department;

public class DepartmentService {
	DepartmentDao department = new JDBCDaoFactory().createDepartmentDao();

	public long create(Department department) {
		return this.department.create(department);
	}

	public Department find(long id) {
		return department.find(id);
	}

	public List<Department> findAll() {
		return department.findAll();
	}

	public void update(Department t) {
		department.update(t);
	}

	public void delete(long id) {
		department.delete(id);
	}

}
