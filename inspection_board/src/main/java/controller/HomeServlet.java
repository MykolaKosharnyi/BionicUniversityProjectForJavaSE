package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(HomeServlet.class);

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		//request.setAttribute("errorMessage", "Password and repaedPassword are different!!!");
		
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
		ConfigurationManager.getInstance().getProperty(ConfigurationManager.ENROLLEE_HOME_PAGE));
	    dispatcher.forward(request, response);

    }
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    }
}