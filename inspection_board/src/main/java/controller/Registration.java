package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Enrollee;

@WebServlet("/registration")
public class Registration extends HttpServlet {
	
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

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // take the ID and retrive all his accounts..
    //    String usrID = request.getParameter("usrID").toString();
          
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
    		ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTRATION_PAGE_PATH));
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
		    request.setAttribute("errorMessage", "Password and repaedPassword are different!!!");
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
		    		ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTRATION_PAGE_PATH));
			dispatcher.forward(request, response);
		}
		
		
		Enrollee enrollee = new Enrollee();
		enrollee.setFirstName(firstName);
		enrollee.setSecondName(secondName);
		enrollee.setEmail(email);
		enrollee.setPhone(phone);
		enrollee.setPassword(password);

        //save in database  
		long idUser = new JDBCDaoFactory().createEnrolleeDao().create(enrollee);
		
		//log in user
		HttpSession session = request.getSession();
		session.setAttribute("userName", email);
		
		session.setAttribute("userId", idUser);
		// setting session to expiry in 30 mins
		session.setMaxInactiveInterval(30 * 60);

		Cookie userName = new Cookie("user", email);
		userName.setMaxAge(30 * 60);
		response.addCookie(userName);
		
		
		response.sendRedirect(request.getContextPath() + "/home");
    }
}