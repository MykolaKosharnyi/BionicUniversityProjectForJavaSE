package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.service.SheetService;

public class ApplicationToDepartmentsPostCommand implements Command {
	SheetService sheetService = SheetService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		sheetService.add( getUserId(request), getDepartmentId(request) );

		return REDIRECT + "set_application_to_departments"
		/*ConfigurationManager.getInstance()
				.getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PAGE)*/;
	}

	private long getUserId(HttpServletRequest request){
		return HttpUtils.getUserIdFromSession(request);
	}
	
	private long getDepartmentId(HttpServletRequest request){
		return HttpUtils.getDepartmentIdFromSession(request);
	}
	
}
