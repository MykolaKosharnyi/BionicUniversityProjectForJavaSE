package controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.command.Command;
import model.service.DepartmentService;
import model.service.impl.DepartmentServiceImpl;

public class AdminDeleteDepartments implements Command {
	private DepartmentService departmentService = DepartmentServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		departmentService.delete(getDepartmentIdFromRequest(request));
		return REDIRECT + "admin_departments"
				/*configurationManger.getProperty(ConfigurationManager.DEPARTMENTS_ADMIN_PATH)*/;
	}
	
	private long getDepartmentIdFromRequest(HttpServletRequest request){
		return Long.parseLong(request.getParameter("departmentId"));
	}

}
