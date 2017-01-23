package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.ConfigurationManager;
import model.service.EnrolleeService;

public class UserEditCommand implements Command {
	
	static Logger logger = Logger.getLogger(UserEditCommand.class);
	
	EnrolleeService enrolleeService = EnrolleeService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		
		request.setAttribute("user", enrolleeService.find(id));
		
		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PAGE);
		
	}

}
