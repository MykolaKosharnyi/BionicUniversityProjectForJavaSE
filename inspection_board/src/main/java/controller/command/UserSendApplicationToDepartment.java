package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.service.impl.SheetServiceImpl;

public class UserSendApplicationToDepartment implements Command {
	private SheetServiceImpl sheetService = SheetServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		sheetService.add( HttpUtils.getUserIdFromSession(request), HttpUtils.getDepartmentIdFromRequestParameter(request) );
		
		return REDIRECT + configurationManger.getProperty(ConfigurationManager.USER_APPLICATION_DEPARTMENT_PATH);
	}	
}
