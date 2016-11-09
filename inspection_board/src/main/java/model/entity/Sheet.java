package model.entity;

import java.util.Map;

import org.apache.log4j.Logger;

public class Sheet {//Ведомость
	private static Sheet instance;
	private Map<Department, Enrollee> sheet;
	
	private static Logger logger = Logger.getLogger(Sheet.class);
	
	public boolean addEnrollee(Department department, Enrollee enrollee){
		
		return false;
	}
	
	private Sheet(){}
	
	/**
	 * Get instance of Sheet.
	 * @return Sheet object.
	 */
	public static Sheet getInstance() {
		if (null == instance) {
			synchronized (Sheet.class) {
				if (null == instance) {
					instance = new Sheet();
					logger.info("create new Sheet");
				}
			}
		}
		logger.info("return Sheet");
		return instance;
	}
	
}
