package model;

public class Administrator {
	
	
	private static Administrator administrator;
	private Sheet sheet;
	
	private Administrator(Sheet sheet){
		this.sheet=sheet;
	}
	
	public Administrator createAdministrator(Sheet sheet){
		administrator = new Administrator(sheet);
		return administrator;
	}
	
	
	public void addEnrollee(Department department, Enrollee enrollee){
		sheet.addEnrollee(department, enrollee);
	}
	
	
}
