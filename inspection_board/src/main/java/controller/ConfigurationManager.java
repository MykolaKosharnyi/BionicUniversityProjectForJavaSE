package controller;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class ConfigurationManager {
	private static ConfigurationManager instance;
	private ResourceBundle resourceBundle;
	// config.properties
	private static final String BUNDLE_NAME = "config";
	public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
	public static final String DATABASE_URL = "DATABASE_URL";
	public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
	public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
	public static final String USER_FORM_PATH = "USER_FORM_PATH";
	public static final String USER_END_PAGE_PATH = "USER_END_PAGE_PATH";
	public static final String ADMIN_PAGE_PATH = "ADMIN_PAGE_PATH";	
	public static final String ADMIN_ADD_USER = "ADMIN_ADD_USER";
	public static final String ADMIN_ADD_EMPLOYEE = "ADMIN_ADD_EMPLOYEE";
	public static final String REGISTRATION_PAGE_PATH = "REGISTRATION_PAGE_PATH";
	public static final String ENROLLEE_HOME_PAGE = "ENROLLEE_HOME_PAGE";
	public static final String USER_EDIT_PAGE = "USER_EDIT_PAGE";
	public static final String CHANGE_SUBJECT_USER = "CHANGE_SUBJECT_USER";
	
	private static Logger logger = Logger.getLogger(ConfigurationManager.class);
	
	public static ConfigurationManager getInstance() {
		if (null == instance) {
			synchronized (ConfigurationManager.class) {
				if (null == instance) {
					instance = new ConfigurationManager();
					instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
					logger.info("create new ConfigurationManager");
				}
			}
		}
		logger.info("return ConfigurationManager");
		return instance;
	}

	public String getProperty(String key) {
		return (String) resourceBundle.getObject(key);
	}
}
