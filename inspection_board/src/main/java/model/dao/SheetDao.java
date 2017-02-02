package model.dao;

import java.util.List;

import model.entity.Department;
import model.entity.Sheet;

public interface SheetDao {
	void add(long idEnrollee, long idDepartment);
	List<Department> findByEnrolleeId(long idEnrollee);
	Sheet getSheet();
	void deleteDepartment(long idDepartment);
	void deleteEnrollee(long idEnrollee);
	void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment);
}
