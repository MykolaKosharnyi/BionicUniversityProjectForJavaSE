package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.service.impl.CertificateServiceImpl;

public class UserDeleteSubject implements Command {
	private CertificateServiceImpl certificateService = CertificateServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		certificateService.deleteSubject( getUserId(request), getSubjectId(request) );
		return REDIRECT + configurationManger.getProperty(ConfigurationManager.USER_SUBJECTS_PATH);
	}
	
	private long getUserId(HttpServletRequest request){
		return HttpUtils.getUserIdFromSession(request);
	}
	
	private long getSubjectId(HttpServletRequest request){
		return HttpUtils.getSubjectIdFromRequestParameter(request);
	}

}
