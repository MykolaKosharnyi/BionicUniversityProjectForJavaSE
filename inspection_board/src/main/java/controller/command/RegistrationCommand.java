package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;

public class RegistrationCommand implements Command {
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return FORWARD + "/WEB-INF/views/user_form.jsp"
				/*configurationManger.getProperty(ConfigurationManager.REGISTRATION_PAGE)*/;
	}

}
