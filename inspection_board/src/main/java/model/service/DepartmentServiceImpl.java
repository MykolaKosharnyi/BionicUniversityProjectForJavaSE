package model.service;

import java.util.List;

import model.dao.DepartmentDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Department;

public class DepartmentServiceImpl implements DepartmentService {
	DepartmentDao department = new JDBCDaoFactory().createDepartmentDao();
		
	@Override
	public long create(Department department) {
		return this.department.create(department);
	}

	@Override
	public Department find(long id) {
		return department.find(id);
	}

	@Override
	public List<Department> findAll() {
		return department.findAll();
	}

	@Override
	public void update(Department t) {
		department.update(t);
	}

	@Override
	public void delete(long id) {
		department.delete(id);
	}

}
