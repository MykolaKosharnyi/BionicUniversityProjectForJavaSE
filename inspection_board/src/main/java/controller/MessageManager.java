package controller;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class MessageManager {
	private static MessageManager instance;
	private ResourceBundle resourceBundle;
	// messages. properties
	private static final String BUNDLE_NAME = "messages";
	public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
	public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
	public static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE";

	private static Logger logger = Logger.getLogger(MessageManager.class);

	public static MessageManager getInstance() {
		if (null == instance) {
			synchronized (MessageManager.class) {
				if (null == instance) {
					instance = new MessageManager();
					instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
					logger.info("create new MessageManager");
				}
			}
		}
		logger.info("return MessageManager");
		return instance;
	}

	public String getProperty(String key) {
		return (String) resourceBundle.getObject(key);
	}
}
