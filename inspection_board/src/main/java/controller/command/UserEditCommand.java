package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.ConfigurationManager;
import model.dao.jdbc.JDBCDaoFactory;

public class UserEditCommand implements Command {
	
	static Logger logger = Logger.getLogger(UserEditCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		
		request.setAttribute("user", new JDBCDaoFactory().createEnrolleeDao().find(id));
		
		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_EDIT_PAGE);
		
	}

}
