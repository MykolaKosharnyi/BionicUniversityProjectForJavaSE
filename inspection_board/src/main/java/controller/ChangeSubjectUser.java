package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import model.dao.CertificateDao;
import model.dao.jdbc.JDBCDaoFactory;

@WebServlet("/add_or_change_subject")
public class ChangeSubjectUser extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PARAM_SUBJECT = "subject";
	private static final String PARAM_SCOPE = "scope";
	static Logger logger = Logger.getLogger(ChangeSubjectUser.class);
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		request.setAttribute("subjects", new JDBCDaoFactory().createSubjectDao().findAll());
		request.setAttribute("user_subjects",  new JDBCDaoFactory().createCertificateDao().find(id));
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
	    		ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHANGE_SUBJECT_USER));
		dispatcher.forward(request, response);

    }
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		int subjectId = Integer.parseInt(request.getParameter(PARAM_SUBJECT));
		int scope = Integer.parseInt(request.getParameter(PARAM_SCOPE));

		//undate in database  
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		
		CertificateDao certificateDao = new JDBCDaoFactory().createCertificateDao();
		certificateDao.addSubject(id, subjectId, scope);
		
		request.setAttribute("subjects", new JDBCDaoFactory().createSubjectDao().findAll());
		request.setAttribute("user_subjects", certificateDao.find(id));
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
	    		ConfigurationManager.getInstance().getProperty(ConfigurationManager.CHANGE_SUBJECT_USER));
		dispatcher.forward(request, response);
		
    }
	

	
}
