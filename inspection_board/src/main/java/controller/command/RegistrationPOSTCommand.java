package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import controller.validation.UserValidation;
import model.entity.User;
import model.service.impl.UserServiceImpl;

public class RegistrationPOSTCommand implements Command {
	UserServiceImpl enrolleeService = UserServiceImpl.getInstance();
	UserValidation validation;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();
		validation = new UserValidation(user);

		if (!validation.validate(request)) {
			request.setAttribute("user", validation.getUser());
			return FORWARD + "/WEB-INF/views/user_form.jsp"
					/*ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTRATION_PAGE)*/;
		}

		long idUser = enrolleeService.create(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		session.setAttribute("userId", idUser);

		return REDIRECT + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_DEPARTMENT_PATH);
	}
	
}
