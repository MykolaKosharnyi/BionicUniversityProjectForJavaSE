package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.dao.jdbc.JDBCDaoFactory;

public class UserSubjectCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		request.setAttribute("subjects", new JDBCDaoFactory().createSubjectDao().findAll());
		request.setAttribute("user_subjects",  new JDBCDaoFactory().createCertificateDao().find(id));
		
	    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHANGE_SUBJECT_USER);
	}

}
