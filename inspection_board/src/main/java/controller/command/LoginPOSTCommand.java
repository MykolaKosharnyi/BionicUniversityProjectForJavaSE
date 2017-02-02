package controller.command;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.entity.User;
import model.service.impl.UserServiceImpl;

public class LoginPOSTCommand implements Command {
	UserServiceImpl enrolleeService = UserServiceImpl.getInstance();
	ConfigurationManager configurationManager = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter(ParameterContants.PARAM_EMAIL);

		if ( checkLogin(request) ) {
			Optional<User> optionalUser = enrolleeService.findByEmail(login);
			User user = optionalUser.get();
			if( user.getRole().equals(User.Role.ADMIN) ) {
				return REDIRECT + "admin_departments"
						/*configurationManager.getProperty(ConfigurationManager.DEPARTMENTS_ADMIN_PATH)*/;
			} else {

				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("user", user);

				return REDIRECT + configurationManager.getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PATH);

			}
		} else {
			request.setAttribute("errorMessage", "E-mail or password is incorect!");
			return FORWARD + configurationManager.getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
		}
	}
	
	private boolean checkLogin(HttpServletRequest request){
		String login = request.getParameter(ParameterContants.PARAM_EMAIL);
		String pass = request.getParameter(ParameterContants.PARAM_PASSWORD);
		
		return enrolleeService.checkLogin(login, pass);
	}

}
