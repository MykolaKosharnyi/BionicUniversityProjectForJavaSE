package model.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import model.service.SheetService;

public class Sheet {//Ведомость
//	private static Sheet instance = new Sheet();
	private Map<Department, List<Enrollee>> sheet = new LinkedHashMap<>();
	
	private static Logger logger = Logger.getLogger(Sheet.class);
	
	public Sheet(){
		//initialize from database
		SheetService sheetService = SheetService.getInstance();
		setSheet(sheetService.getSheet());
	}

	
	/**
	 * Get instance of Sheet.
	 * @return Sheet object.
	 */
//	public static Sheet getInstance() {
//		logger.info("return Sheet");
//		return instance;
//	}
	
	
	///
//	private static class Holder{
//    	static final Sheet INSTANCE = new Sheet(); 
//    }
//    
//    public static Sheet getInstance(){
//    	logger.info("get instance of Sheet");
//    	return Holder.INSTANCE;
//    }
	//
	
	public Map<Department, List<Enrollee>> getSheet() {
		return sheet;
	}

	public void setSheet(Map<Department, List<Enrollee>> sheet) {
		this.sheet = sheet;
	}
	
	public void addEnrollee(Department department, Enrollee enrollee){
		List<Enrollee> enrollees = (sheet.get(department)==null) ? new ArrayList<Enrollee>() : sheet.get(department);
		enrollees.add(enrollee);
		
		sheet.put(department, enrollees);
	}
	
	public void deleteEnrollee(Department department, Enrollee enrollee){
		List<Enrollee> enrollees = sheet.get(department);
		enrollees.remove(enrollee);
		
		sheet.put(department, enrollees);
	}
	
}
