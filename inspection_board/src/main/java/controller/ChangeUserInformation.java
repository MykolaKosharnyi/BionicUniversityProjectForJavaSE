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

import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Enrollee;

@WebServlet("/user_edit")
public class ChangeUserInformation extends HttpServlet {
	static Logger logger = Logger.getLogger(ChangeUserInformation.class);
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PARAM_FIRSTNAME = "firstname";
	private static final String PARAM_SECONDNAME = "secondname";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PHONE = "phone";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_REPEAT_PASSWORD = "repeat_password";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		
		request.setAttribute("user", new JDBCDaoFactory().createEnrolleeDao().find(id));

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
				ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PAGE));
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter(PARAM_FIRSTNAME);
		String secondName = request.getParameter(PARAM_SECONDNAME);
		String email = request.getParameter(PARAM_EMAIL);
		String phone = request.getParameter(PARAM_PHONE);
		String password = request.getParameter(PARAM_PASSWORD);
		String repeatPassword = request.getParameter(PARAM_REPEAT_PASSWORD);
		
		if(!password.equals(repeatPassword)){
			request.setAttribute("user", new JDBCDaoFactory().createEnrolleeDao().find(1));
		    request.setAttribute("errorMessage", "Password and repeadPassword are different!!!");
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
		    		ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PAGE));
			dispatcher.forward(request, response);
		}
		
		
		Enrollee enrollee = new Enrollee();
		
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		enrollee.setId(id);
		enrollee.setFirstName(firstName);
		enrollee.setSecondName(secondName);
		enrollee.setEmail(email);
		enrollee.setPhone(phone);
		enrollee.setPassword(password);

        //undate in database  
		new JDBCDaoFactory().createEnrolleeDao().update(enrollee);
		
		response.sendRedirect(request.getContextPath() + "/home");
    }
}
