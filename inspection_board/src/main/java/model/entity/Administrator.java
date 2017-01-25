package model.entity;

import org.apache.log4j.Logger;

public class Administrator {
	
	private static Logger logger = Logger.getLogger(Administrator.class);
	
	private Sheet sheet;
	
	private Administrator(Sheet sheet){
		this.sheet=sheet;
	}
	
	private static class Holder{
    	static final Administrator INSTANCE = new Administrator(new Sheet()); 
    }
    
    public static Administrator getInstance(){
    	logger.info("get instance of Administrator");
    	return Holder.INSTANCE;
    }
	
	public void addEnrollee(Department department, Enrollee enrollee){
		sheet.addEnrollee(department, enrollee);
	}
	
	public void deleteEnrollee(Department department, Enrollee enrollee){
		sheet.deleteEnrollee(department, enrollee);
	}
	
}
