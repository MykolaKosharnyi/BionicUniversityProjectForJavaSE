package controller.command;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.ConfigurationManager;
import model.entity.User;
import model.service.UserService;

public class UserEditCommand implements Command {
	
	static Logger logger = Logger.getLogger(UserEditCommand.class);
	
	UserService enrolleeService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		
		Optional<User> optionalUser = enrolleeService.find(id);
		request.setAttribute("user", optionalUser.get());
		
		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PAGE);
		
	}

}
