package model.service;

import model.dao.SheetDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Sheet;

public class SheetService{
	SheetDao sheetDao = new JDBCDaoFactory().createSheetDao();
	
	public void add(long idEnrollee, long idDepartment) {
		sheetDao.add(idEnrollee, idDepartment);
	}

	public Sheet getSheet() {
		return sheetDao.getSheet();
	}

	public void deleteDepartment(long idDepartment) {
		sheetDao.deleteDepartment(idDepartment);
	}

	public void deleteEnrollee(long idEnrollee) {
		sheetDao.deleteEnrollee(idEnrollee);		
	}

	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		sheetDao.deleteEnrolleeFromDepartment(idEnrollee, idDepartment);
	}

}
