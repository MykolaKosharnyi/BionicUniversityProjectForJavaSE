package model.service;

import model.entity.Department;
import model.entity.Enrollee;
import model.entity.Sheet;

interface SheetService {
	void add(Enrollee enrollee, Department department);
	Sheet getSheet();
	Sheet getSheetConcreteDepartment(long idDepartment);
	void deleteDepartment(long idDepartment);
	void deleteEnrollee(long idEnrollee);
	void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment);
}
