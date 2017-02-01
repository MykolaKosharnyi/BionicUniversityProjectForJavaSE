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

public class UserEditPostCommand implements Command {

	static Logger logger = Logger.getLogger(UserEditPostCommand.class);

	UserService enrolleeService = UserService.getInstance();

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
			HttpSession session = request.getSession();
			long id = (long) session.getAttribute("userId");
			Optional<User> optionalUser = enrolleeService.find(id);
			request.setAttribute("user", optionalUser.get());
			request.setAttribute("errorMessage", "Password and repeadPassword are different!!!");

			return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PAGE);
		}

		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");

		// undate in database
		enrolleeService.update(new User.Builder().setId(id).setFirstName(firstName).setSecondName(secondName)
				.setEmail(email).setPhone(phone).setPassword(password).build());

		return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PATH);
	}

}
