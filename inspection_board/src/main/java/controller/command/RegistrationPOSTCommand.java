package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.ConfigurationManager;
import model.entity.User;
import model.service.impl.UserServiceImpl;

public class RegistrationPOSTCommand implements Command {

	static Logger logger = Logger.getLogger(RegistrationPOSTCommand.class);

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
			request.setAttribute("errorMessage", "Password and repeadPassword are different!!!");

			return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
		}

		User user = new User();
		user.setFirstName(firstName);
		user.setSecondName(secondName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);

		// save in database
		long idUser = enrolleeService.create(user);

		// log in user
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		session.setAttribute("userId", idUser);

		return REDIRECT + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_DEPARTMENT_PATH);
	}

}
