package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.service.SheetService;

public class UserSendApplicationToDepartment implements Command {
	SheetService sheetService = SheetService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sheetService.add( HttpUtils.getUserIdFromSession(request), HttpUtils.getDepartmentIdFromRequestParameter(request) );
		
		return REDIRECT + 
			ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_DEPARTMENT_PATH);
	}	
}
