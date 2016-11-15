package model.entity;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Sheet {//Ведомость
	private static Sheet instance;
	private Map<Department, List<Enrollee>> sheet;
	
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

	public Map<Department, List<Enrollee>> getSheet() {
		return sheet;
	}

	public void setSheet(Map<Department, List<Enrollee>> sheet) {
		this.sheet = sheet;
	}
	
}
