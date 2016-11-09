package model.entity;

import org.apache.log4j.Logger;

public class Administrator {
	
	private static Logger logger = Logger.getLogger(Administrator.class);
	
	private static Administrator instance;
	private Sheet sheet;
	
	private Administrator(Sheet sheet){
		this.sheet=sheet;
	}
	
	public static Administrator getInstance(Sheet sheet) {
		if (null == instance) {
			synchronized (Administrator.class) {
				if (null == instance) {
					instance = new Administrator(sheet);
					logger.info("create new Administrator");
				}
			}
		}
		logger.info("return Administrator");
		return instance;
	}
	
	
	public void addEnrollee(Department department, Enrollee enrollee){
		sheet.addEnrollee(department, enrollee);
	}
	
	
}
