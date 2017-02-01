package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.service.CertificateService;
import model.service.SubjectService;

public class UserSubjectCommand implements Command {

	CertificateService certificateService = CertificateService.getInstance();
	SubjectService subjectService = SubjectService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("subjects", subjectService.findAll());
		request.setAttribute("user_subjects", certificateService.find( HttpUtils.getUserIdFromSession(request) ));

		return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHANGE_SUBJECT_USER);
	}
}
