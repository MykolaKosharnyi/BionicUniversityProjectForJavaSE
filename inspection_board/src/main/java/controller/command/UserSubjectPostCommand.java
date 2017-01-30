package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.service.CertificateService;
import model.service.SubjectService;

public class UserSubjectPostCommand implements Command {

	CertificateService certificateService = CertificateService.getInstance();
	SubjectService subjectService = SubjectService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int subjectId = Integer.parseInt(request.getParameter(ParameterContants.PARAM_SUBJECT));
		int scope = Integer.parseInt(request.getParameter(ParameterContants.PARAM_SCOPE));

		// undate in database
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");

		certificateService.addSubject(id, subjectId, scope);

		request.setAttribute("subjects", subjectService.findAll());
		request.setAttribute("user_subjects", certificateService.find(id));

		return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHANGE_SUBJECT_USER);
	}

}
