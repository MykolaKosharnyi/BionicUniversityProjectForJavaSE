package controller.command;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.entity.User;
import model.service.UserService;

public class LoginPOSTCommand implements Command {
	UserService enrolleeService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter(ParameterContants.PARAM_EMAIL);

		if ( checkLogin(request) ) {
			Optional<User> optionalUser = enrolleeService.findByEmail(login);
			User user = optionalUser.get();
			if ( user.getRole().equals(User.Role.ADMIN) ) {
				return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
			} else {

				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("user", user);

				return REDIRECT + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PATH);

			}
		} else {
			request.setAttribute("errorMessage", "E-mail or password is incorect!");
			return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
		}
	}
	
	private boolean checkLogin(HttpServletRequest request){
		String login = request.getParameter(ParameterContants.PARAM_EMAIL);
		String pass = request.getParameter(ParameterContants.PARAM_PASSWORD);
		
		return enrolleeService.checkLogin(login, pass);
	}

}
