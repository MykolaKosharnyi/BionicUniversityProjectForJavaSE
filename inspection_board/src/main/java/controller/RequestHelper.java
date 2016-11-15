package controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import controller.command.NoCommand;

public class RequestHelper {
	
	private static Logger logger = Logger.getLogger(RequestHelper.class);
	private static RequestHelper instance = null;
	private HashMap<String, Command> commands = new HashMap<String, Command>();

	private RequestHelper() {
	
		
		commands.put("login", new LoginCommand());
		commands.put("delete_user_subject", new DeleteUserSubjectCommand());
		
		/*commands.put("user_page", new UserPageCommand());
		commands.put("user_edit_page", new UserEditPageCommand());
		commands.put("rating_enrollee", new RatingEnrolleeCommand());
		commands.put("submit_application", new SubmitApplicationCommand());
		*/
	}

	public Command getCommand(HttpServletRequest request) {

		String action = request.getParameter("command");
		
		Command command = commands.get(action);
		logger.info("command = " + action);
		if (command == null) {

			command = new NoCommand();
			logger.info("return new NoCommand");
		}
		return command;
	}
	
	public static RequestHelper getInstance() {
		if (null == instance) {
			synchronized (RequestHelper.class) {
				if (null == instance) {
					instance = new RequestHelper();
					logger.info("create new RequestHelper");
				}
			}
		}
		logger.info("return RequestHelper");
		return instance;
	}
}
