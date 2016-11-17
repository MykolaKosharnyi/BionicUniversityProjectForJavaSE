package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import controller.ConfigurationManager;
import controller.MessageManager;

public class LoginCommand implements Command {

	static Logger logger = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;

		try {
			page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
		} catch (Exception ex) {
			request.setAttribute("errorMessage",
					MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
			logger.error(MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
		}

		return page;
	}

}
