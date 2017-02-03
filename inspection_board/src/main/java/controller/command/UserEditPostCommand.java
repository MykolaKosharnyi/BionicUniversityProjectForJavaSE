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
	private UserServiceImpl enrolleeService = UserServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = new User();
		UserValidation validation = new UserValidation(user);

		if (!validation.validate(request)) {
			request.setAttribute("user", validation.getUser());
			return FORWARD + configurationManger.getProperty(ConfigurationManager.USER_EDIT_PAGE);
		}

		enrolleeService.update(user);

		return FORWARD + configurationManger.getProperty(ConfigurationManager.USER_EDIT_PATH);
	}

}
