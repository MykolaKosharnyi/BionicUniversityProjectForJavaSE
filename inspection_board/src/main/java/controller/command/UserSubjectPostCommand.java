package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.dao.CertificateDao;
import model.dao.jdbc.JDBCDaoFactory;

public class UserSubjectPostCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int subjectId = Integer.parseInt(request.getParameter(ParameterContants.PARAM_SUBJECT));
		int scope = Integer.parseInt(request.getParameter(ParameterContants.PARAM_SCOPE));

		//undate in database  
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		
		CertificateDao certificateDao = new JDBCDaoFactory().createCertificateDao();
		certificateDao.add(id, subjectId, scope);
		
		request.setAttribute("subjects", new JDBCDaoFactory().createSubjectDao().findAll());
		request.setAttribute("user_subjects", certificateDao.find(id));
		
	    return	ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHANGE_SUBJECT_USER);
	}

}
