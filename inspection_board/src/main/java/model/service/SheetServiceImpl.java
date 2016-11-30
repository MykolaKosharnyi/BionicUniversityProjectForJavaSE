package model.service;

import model.dao.SheetDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Sheet;

public class SheetServiceImpl implements SheetService{
	SheetDao sheetDao = new JDBCDaoFactory().createSheetDao();
	
	@Override
	public void add(long idEnrollee, long idDepartment) {
		sheetDao.add(idEnrollee, idDepartment);
	}

	@Override
	public Sheet getSheet() {
		return sheetDao.getSheet();
	}

	@Override
	public void deleteDepartment(long idDepartment) {
		sheetDao.deleteDepartment(idDepartment);
	}

	@Override
	public void deleteEnrollee(long idEnrollee) {
		sheetDao.deleteEnrollee(idEnrollee);		
	}

	@Override
	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		sheetDao.deleteEnrolleeFromDepartment(idEnrollee, idDepartment);
	}

}
