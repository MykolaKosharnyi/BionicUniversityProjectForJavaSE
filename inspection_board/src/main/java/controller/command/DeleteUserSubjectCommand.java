package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.dao.CertificateDao;
import model.dao.jdbc.JDBCDaoFactory;

public class DeleteUserSubjectCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long subjectId = Long.parseLong(request.getParameter("subjectId"));
		
		//undate in database 
		CertificateDao certificateDao = new JDBCDaoFactory().createCertificateDao();
		
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		certificateDao.delete(id, subjectId);

		request.setAttribute("subjects", new JDBCDaoFactory().createSubjectDao().findAll());
		request.setAttribute("user_subjects", certificateDao.find(id));
		
		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHANGE_SUBJECT_USER);
	}

}
