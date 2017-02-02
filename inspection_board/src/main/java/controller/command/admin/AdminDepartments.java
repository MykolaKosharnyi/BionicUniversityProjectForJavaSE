package controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.command.Command;
import model.service.impl.DepartmentServiceImpl;
import model.service.impl.SubjectServiceImpl;

public class AdminDepartments implements Command {
	SubjectServiceImpl subjectService = SubjectServiceImpl.getInstance();
	DepartmentServiceImpl departmentService = DepartmentServiceImpl.getInstance();
	ConfigurationManager configurationManager = ConfigurationManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("subjects", subjectService.findAll());
		request.setAttribute("departments", departmentService.findAll());
		return FORWARD + "/WEB-INF/views/admin_pages/admin_departments.jsp"
				/*configurationManager.getProperty(ConfigurationManager.DEPARTMENTS_ADMIN)*/;
	}

}
