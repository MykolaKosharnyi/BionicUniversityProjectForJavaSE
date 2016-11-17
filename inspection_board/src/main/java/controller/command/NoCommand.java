package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;

public class NoCommand implements Command {
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
	}
}
