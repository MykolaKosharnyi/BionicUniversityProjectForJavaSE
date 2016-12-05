package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import model.service.DepartmentServiceImpl;
import model.service.DepartmentService;

public class ApplicationToDepartments implements Command {
	DepartmentService service = new DepartmentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("departments", service.findAll());
		
		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PAGE);
	}
}
