package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.service.CertificateService;

public class UserAddSubject implements Command {
	CertificateService certificateService = CertificateService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		certificateService.addSubject( getUserId(request), getSubjectId(request), getScopeId(request) );
		return REDIRECT + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_SUBJECTS_PATH);
	}
	
	private long getUserId(HttpServletRequest request){
		return HttpUtils.getUserIdFromSession(request);
	}
	
	private long getSubjectId(HttpServletRequest request){
		return HttpUtils.getSubjectIdFromRequestParameter(request);
	}
	
	private int getScopeId(HttpServletRequest request){
		return Integer.parseInt(request.getParameter(ParameterContants.PARAM_SCOPE));
	}

}
