package controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.command.Command;
import model.entity.Subject;
import model.service.SubjectService;
import model.service.impl.SubjectServiceImpl;

public class AdminAddSubject implements Command {
	SubjectService subjectService = SubjectServiceImpl.getInstance();
	ConfigurationManager configurationManger = ConfigurationManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		subjectService.create(getObjectFromRequest(request));
		return REDIRECT + "admin_subjects"
				/*configurationManger.getProperty(ConfigurationManager.SUBJECT_ADMIN_PATH)*/;
	}
	
	private Subject getObjectFromRequest(HttpServletRequest request){
		 return new Subject.Builder()
					.setName(getSubjectNameFromRequest(request))
					.build();
	}
	
	private String getSubjectNameFromRequest(HttpServletRequest request){
		return request.getParameter("subjectName");
	}

}
