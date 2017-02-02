package model.service;

import model.entity.Sheet;

import java.util.List;

import model.entity.Department;

public interface SheetService {
	void add(long idEnrollee, long idDepartment);
	List<Department> findByEnrolleeId(long idEnrollee);
	Sheet getSheet();
	void deleteDepartment(long idDepartment);
	void deleteEnrollee(long idEnrollee);
	void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment);
}
