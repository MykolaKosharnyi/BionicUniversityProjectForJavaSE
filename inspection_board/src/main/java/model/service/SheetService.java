package model.service;

import java.util.List;
import java.util.Map;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.SheetDao;
import model.entity.Administrator;
import model.entity.Department;
import model.entity.Enrollee;

public class SheetService{	
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	private SheetService(){}
	
	private static class Holder{
    	static final SheetService INSTANCE = new SheetService(); 
    }
    
    public static SheetService getInstance(){
    	return Holder.INSTANCE;
    }
	
	public void add(long idEnrollee, long idDepartment) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
			
			// safe changes to database
			dao.add(idEnrollee, idDepartment);
			
			connection.commit();
		}
			// Change by Administrator in Sheet
			// get Enrollee
			Enrollee enrollee = EnrolleeService.getInstance().find(idEnrollee);		
			// get Department
			Department department = DepartmentService.getInstance().find(idDepartment);
						
			Administrator.getInstance().addEnrollee(department, enrollee);
	}

	public Map<Department, List<Enrollee>> getSheet() {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
			Map<Department, List<Enrollee>> result = dao.getSheet();
			connection.commit();
			return result;
		}
	}

	public void deleteDepartment(long idDepartment) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
	
			// safe changes to database
			dao.deleteDepartment(idDepartment);
			
			connection.commit();
		}	
			// for changing by Administrator in Sheet it doesn't realized 
	}

	public void deleteEnrollee(long idEnrollee) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
	
			// safe changes to database
			dao.deleteEnrollee(idEnrollee);		
			
			connection.commit();
		}	
			// for changing by Administrator in Sheet it doesn't realized 
	}

	public void deleteEnrolleeFromDepartment(long idEnrollee, long idDepartment) {
		try( DaoConnection connection = daoFactory.getConnection() ){
			connection.begin();
			SheetDao dao = daoFactory.createSheetDao(connection);
	
			//safe changes to database
			dao.deleteEnrolleeFromDepartment(idEnrollee, idDepartment);
			
			connection.commit();
		}
			
			// Change by Administrator in Sheet
			// get Enrollee
			Enrollee enrollee = EnrolleeService.getInstance().find(idEnrollee);
			// get Department
			Department department = DepartmentService.getInstance().find(idDepartment);

			Administrator.getInstance().deleteEnrollee(department, enrollee);
	}

}
