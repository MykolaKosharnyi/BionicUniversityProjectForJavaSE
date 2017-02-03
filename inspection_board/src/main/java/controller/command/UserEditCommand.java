package controller.command;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.entity.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

public class UserEditCommand implements Command {
	private UserService enrolleeService = UserServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Optional<User> optionalUser = enrolleeService.find( HttpUtils.getUserIdFromSession(request) );
		request.setAttribute("user", optionalUser.get());

		return FORWARD + configurationManger.getProperty(ConfigurationManager.USER_EDIT_PAGE);
	}
}
