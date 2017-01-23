package model.service;

import model.dao.SheetDao;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Administrator;
import model.entity.Department;
import model.entity.Enrollee;
import model.entity.Sheet;

public class SheetService{
	SheetDao sheetDao = new JDBCDaoFactory().createSheetDao();
	Administrator administrator = Administrator.getInstance();
	
	private SheetService(){}
	
	private static class Holder{
    	static final SheetService INSTANCE = new SheetService(); 
    }
    
    public static SheetService getInstance(){
    	return Holder.INSTANCE;
    }
	
	public void add(long idEnrollee, long idDepartment) {
		// safe changes to database
		sheetDao.add(idEnrollee, idDepartment);
		
		// Change by Administrator in Sheet
		// get Enrollee
		Enrollee enrollee = EnrolleeService.getInstance().find(idEnrollee);		
		// get Department
		Department department = DepartmentService.getInstance().find(idDepartment);
		
		administrator.addEnrollee(department, enrollee);
	}

	public Sheet getSheet() {
		return sheetDao.getSheet();
	}

	public void deleteDepartment(long idDepartment) {
		// safe changes to database
		sheetDao.deleteDepartment(idDepartment);
		
		// for changing by Administrator in Sheet it doesn't realized 
	}

	public void deleteEnrollee(long idEnrollee) {
		// safe changes to database
		sheetDao.deleteEnrollee(idEnrollee);		
		
		// for changing by Administrator in Sheet it doesn't realized 
	}

	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		//safe changes to database
		sheetDao.deleteEnrolleeFromDepartment(idEnrollee, idDepartment);
		
		// Change by Administrator in Sheet
		// get Enrollee
		Enrollee enrollee = EnrolleeService.getInstance().find(idEnrollee);
		// get Department
		Department department = DepartmentService.getInstance().find(idDepartment);

		administrator.deleteEnrollee(department, enrollee);
	}

}
