package controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.ParameterContants;
import controller.command.Command;
import model.service.SubjectService;
import model.service.impl.SubjectServiceImpl;

public class AdminDeleteSubject implements Command {
	private SubjectService subjectService = SubjectServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		subjectService.delete(getSubjectIdFromRequest(request));
		return REDIRECT + "admin_subjects"
				/*configurationManger.getProperty(ConfigurationManager.SUBJECT_ADMIN_PATH)*/;
	}
	
	private long getSubjectIdFromRequest(HttpServletRequest request){
		return Long.parseLong(request.getParameter(ParameterContants.PARAM_SUBJECT_ID));
	}

}
