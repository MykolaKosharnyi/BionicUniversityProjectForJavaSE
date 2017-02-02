package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return FORWARD + "/WEB-INF/views/user_form.jsp"
				/*ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTRATION_PAGE)*/;
	}

}
