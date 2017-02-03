package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;

public class NoCommand implements Command {
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return FORWARD + configurationManger.getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
	}
}
