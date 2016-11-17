package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.ConfigurationManager;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Enrollee;

public class UserEditPostCommand implements Command {
	
	static Logger logger = Logger.getLogger(UserEditPostCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter(ParameterContants.PARAM_FIRSTNAME);
		String secondName = request.getParameter(ParameterContants.PARAM_SECONDNAME);
		String email = request.getParameter(ParameterContants.PARAM_EMAIL);
		String phone = request.getParameter(ParameterContants.PARAM_PHONE);
		String password = request.getParameter(ParameterContants.PARAM_PASSWORD);
		String repeatPassword = request.getParameter(ParameterContants.PARAM_REPEAT_PASSWORD);
		
		if(!password.equals(repeatPassword)){
			HttpSession session = request.getSession();
			long id = (long) session.getAttribute("userId");
			request.setAttribute("user", new JDBCDaoFactory().createEnrolleeDao().find(id));
		    request.setAttribute("errorMessage", "Password and repeadPassword are different!!!");
			
			return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PAGE);
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
		
		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ENROLLEE_HOME_PAGE);
	}

}
