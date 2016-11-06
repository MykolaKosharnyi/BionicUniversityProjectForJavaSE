package model;

import java.util.Map;

public class Sheet {//Ведомость
	private static Sheet sheetClass = new Sheet();
	private Map<Department, Enrollee> sheet;
	
	public boolean addEnrollee(Department department, Enrollee enrollee){
		
		return false;
	}
	
	private Sheet(){}
	
	public static Sheet createSheet(){
		return sheetClass;
	}
	
}
