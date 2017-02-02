package controller;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class ConfigurationManager {
	private static ConfigurationManager instance;
	private ResourceBundle resourceBundle;
	// config.properties
	private static final String BUNDLE_NAME = "config";
	public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
	public static final String REGISTRATION_PAGE = "REGISTRATION_PAGE";
	public static final String USER_EDIT_PAGE = "USER_EDIT_PAGE";
	public static final String CHANGE_SUBJECT_USER = "CHANGE_SUBJECT_USER";
	public static final String USER_APPLICATION_TO_DEPARTMENT_PAGE = "USER_APPLICATION_TO_DEPARTMENT_PAGE";
	public static final String USER_APPLICATION_DEPARTMENT_PATH = "USER_APPLICATION_DEPARTMENT_PATH";
	public static final String USER_SUBJECTS_PATH = "USER_SUBJECTS_PATH";
	public static final String USER_APPLICATION_TO_DEPARTMENT_PATH = "USER_APPLICATION_TO_DEPARTMENT_PATH";
	public static final String USER_EDIT_PATH = "USER_EDIT_PATH";
	public static final String SHEET_PAGE = "SHEET_PAGE";
	
	public static final String SUBJECTS_ADMIN = "SUBJECTS_ADMIN";
	public static final String DEPARTMENTS_ADMIN = "DEPARTMENTS_ADMIN";
	
	public static final String DEPARTMENTS_ADMIN_PATH = "DEPARTMENTS_ADMIN_PATH";
	
	public static final String PAGE_404_VIEW_NAME = "PAGE_404_VIEW_NAME";
    public static final String STORAGE_EXCEPTION_PAGE_VIEW_NAME = "STORAGE_EXCEPTION_PAGE_VIEW_NAME";
    public static final String GENERAL_ERROR_PAGE_VIEW_NAME = "GENERAL_ERROR_PAGE_VIEW_NAME";
    public static final String ACCESS_DENIED_PAGE_VIEW_NAME = "ACCESS_DENIED_PAGE_VIEW_NAME";

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
