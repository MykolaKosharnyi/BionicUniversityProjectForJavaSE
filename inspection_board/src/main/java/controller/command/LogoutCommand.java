package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;

public class LogoutCommand implements Command {
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return FORWARD + configurationManger.getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
	}

}
