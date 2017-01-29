package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.service.SheetService;

public class ApplicationToDepartmentsPostCommand implements Command {
	SheetService sheetService = SheetService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//get the enrollee id
		HttpSession session = request.getSession();
		long enrolleeId = (long) session.getAttribute("userId");
		
		//get the department id
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		
		sheetService.add(enrolleeId, departmentId);
		
		return "redirect:" + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PAGE);
	}

}
