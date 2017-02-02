package model.service;

import model.entity.Sheet;

public interface SheetService {
	void add(long idEnrollee, long idDepartment);
	Sheet getSheet();
	void deleteDepartment(long idDepartment);
	void deleteEnrollee(long idEnrollee);
	void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment);
}
