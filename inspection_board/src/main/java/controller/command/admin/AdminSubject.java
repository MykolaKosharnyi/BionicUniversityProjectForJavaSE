package controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.command.Command;
import model.service.impl.SubjectServiceImpl;

public class AdminSubject implements Command {
	private SubjectServiceImpl subjectService = SubjectServiceImpl.getInstance();
	private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("subjects", subjectService.findAll());
		return FORWARD + "/WEB-INF/views/admin_pages/admin_subjects.jsp"
				/*configurationManager.getProperty(ConfigurationManager.SUBJECTS_ADMIN)*/;
	}

}
