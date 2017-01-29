package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import model.service.SheetService;

public class Sheet {// Ведомость
	// private static Sheet instance = new Sheet();
	private Map<Department, List<User>> sheet;

	private static Logger logger = Logger.getLogger(Sheet.class);

	public Sheet() {
		SheetService sheetService = SheetService.getInstance();
		setSheet(sheetService.getSheet());
		logger.info("creating Sheet instace done successful");
	}

	public Map<Department, List<User>> getSheet() {
		return sheet;
	}

	public void setSheet(Map<Department, List<User>> sheet) {
		this.sheet = sheet;
	}

	public void addEnrollee(Department department, User enrollee) {
		List<User> enrollees = (sheet.get(department) == null) ? new ArrayList<User>() : sheet.get(department);
		enrollees.add(enrollee);

		sheet.put(department, enrollees);
	}

	public void deleteEnrollee(Department department, User enrollee) {
		List<User> enrollees = sheet.get(department);
		enrollees.remove(enrollee);

		sheet.put(department, enrollees);
	}

}
