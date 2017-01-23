package controller.command;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class RequestHelper {
	
	private static Logger logger = Logger.getLogger(RequestHelper.class);
	private static RequestHelper instance = null;
	private HashMap<String, Command> commands = new HashMap<String, Command>();

	private RequestHelper() {
	
		commands.put("/login", new LoginCommand());
		commands.put("/login_post", new LoginPOSTCommand());
		commands.put("/registration", new RegistrationCommand());
		commands.put("/registration_post", new RegistrationPOSTCommand());
		commands.put("/user_edit", new UserEditCommand());
		commands.put("/user_edit_post", new UserEditPostCommand());
		commands.put("/home", new HomeCommand());
		commands.put("/logout", new LogoutCommand());
		commands.put("/set_application_to_departments", new ApplicationToDepartments());
		commands.put("/set_application_to_departments_post", new ApplicationToDepartmentsPostCommand());
		commands.put("/add_or_change_subject", new UserSubjectCommand());
		commands.put("/add_or_change_post_subject", new UserSubjectPostCommand());	
		commands.put("/delete_user_subject", new DeleteUserSubjectCommand());
		
	}

	public Command getCommand(HttpServletRequest request) {

		String action = request.getPathInfo();
		
		
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
