package controller.command;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.entity.User;
import model.service.impl.UserServiceImpl;

public class UserEditPostCommand implements Command {
	UserServiceImpl enrolleeService = UserServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter(ParameterContants.PARAM_FIRSTNAME);
		String secondName = request.getParameter(ParameterContants.PARAM_SECONDNAME);
		String email = request.getParameter(ParameterContants.PARAM_EMAIL);
		String phone = request.getParameter(ParameterContants.PARAM_PHONE);
		String password = request.getParameter(ParameterContants.PARAM_PASSWORD);
		String repeatPassword = request.getParameter(ParameterContants.PARAM_REPEAT_PASSWORD);

		if (!password.equals(repeatPassword)) {
			Optional<User> optionalUser = enrolleeService.find( HttpUtils.getUserIdFromSession(request) );
			request.setAttribute("user", optionalUser.get());
			request.setAttribute("errorMessage", "Password and repeadPassword was different!");

			return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PAGE);
		}

		enrolleeService.update(new User.Builder()
				.setId( HttpUtils.getUserIdFromSession(request) )
				.setFirstName(firstName).setSecondName(secondName)
				.setEmail(email)
				.setPhone(phone)
				.setPassword(password)
				.build());

		return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PATH);
	}
	
	

}
