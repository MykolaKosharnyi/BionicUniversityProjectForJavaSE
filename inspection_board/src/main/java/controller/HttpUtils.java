package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filter.AccessDeniedException;
import model.dao.exception.DaoException;
import model.entity.User;
import model.service.impl.UserServiceImpl;

import static controller.ApplicationResources.*;
import static java.util.Objects.nonNull;

import java.io.IOException;
import java.util.NoSuchElementException;

public final class HttpUtils {
	
	private static final ConfigurationManager configurationManager = ConfigurationManager.getInstance();
	private static final String EXCEPTION_DURING_REDIRECTION_TEXT =
            "User id = %d. Exception during redirection to '%s'.";
	private static final UserServiceImpl userService = UserServiceImpl.getInstance();
	
	/**
     * Retrieves a current user's id from the session.
     * @return id of the current signed in user or 0 if a user has not been authenticated yet
     */
    public static long getUserIdFromSession(HttpServletRequest request) {
    	Long id = (Long) request.getSession().getAttribute(CURRENT_USER_ID_ATTR_NAME);
        return nonNull(id) ? id : 0;
    }
    
    /**
     * Retrieves a current department's id from the session.
     * @return id of the current parameter request or 0 if a department don't sent value
     */
    public static long getDepartmentIdFromRequestParameter(HttpServletRequest request) {
    	Long id = (Long) Long.parseLong(request.getParameter(ID_DEPARTMENT_ATTR_NAME));
        return nonNull(id) ? id : 0;
    }
    
    /**
     * Retrieves a current subject's id from the session.
     * @return id of the current parameter request or 0 if a subject don't sent value
     */
    public static long getSubjectIdFromRequestParameter(HttpServletRequest request) {
    	Long id = (Long) Long.parseLong(request.getParameter(ID_SUBJECT_ATTR_NAME));
        return nonNull(id) ? id : 0;
    }
    
    
    /**
     * Retrieves a user object from the db for the current user from the request.
     */
    public static User getCurrentUserFromFromDb(HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(CURRENT_USER_ATTR_NAME);
        return userService.find(user.getId()).get();
    }
    
    /**
     * Sends a redirect on this response.
     */
    public static void sendRedirect(HttpServletRequest request, HttpServletResponse response,
                                    String redirectUri) {
        try {
            response.sendRedirect(redirectUri);

        } catch (IOException e) {
            String message = String.format(EXCEPTION_DURING_REDIRECTION_TEXT,
                    HttpUtils.getUserIdFromSession(request), redirectUri);

            throw new RuntimeException(message, e);
        }
    }
    
    /**
     * Returns an appropriate view name for this exception.
     */
    public static String getErrorViewName(Throwable exception) {
        if (exception instanceof DaoException) {        	
            return configurationManager.getProperty(ConfigurationManager.STORAGE_EXCEPTION_PAGE_VIEW_NAME);
        }

        if (exception instanceof NoSuchElementException) {        	
            return configurationManager.getProperty(ConfigurationManager.PAGE_404_VIEW_NAME);
        }

        if (exception instanceof AccessDeniedException) {        	
            return configurationManager.getProperty(ConfigurationManager.ACCESS_DENIED_PAGE_VIEW_NAME);
        }
  
        return configurationManager.getProperty(ConfigurationManager.GENERAL_ERROR_PAGE_VIEW_NAME);
    }
}
