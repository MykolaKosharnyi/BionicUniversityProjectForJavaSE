package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.validation.UserValidation;
import model.entity.User;
import model.service.impl.UserServiceImpl;

public class UserEditPostCommand implements Command {
	UserServiceImpl enrolleeService = UserServiceImpl.getInstance();
	ConfigurationManager configurationManger = ConfigurationManager.getInstance();
	UserValidation validation;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = new User();
		validation = new UserValidation(user);

		if (!validation.validate(request)) {
			request.setAttribute("user", validation.getUser());
			return FORWARD + configurationManger.getProperty(ConfigurationManager.USER_EDIT_PAGE);
		}

		enrolleeService.update(user);

		return FORWARD + configurationManger.getProperty(ConfigurationManager.USER_EDIT_PATH);
	}
	
	

}
