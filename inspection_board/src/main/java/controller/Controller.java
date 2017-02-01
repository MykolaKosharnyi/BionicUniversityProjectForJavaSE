package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import controller.command.Command;
import controller.command.RequestHelper;

public class Controller extends HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Controller.class);
	private static final String DISPATCHING_TO_THE_VIEW_NAME = "Dispatching to the viewName = '%s'.";
	private static final String USER_ID_REQUEST_URI = "User id = '%s'. requestURI = '%s'";
	private static final String INCORRECT_THE_DISPATCH_TYPE =
            "Incorrect the dispatch type of the viewName: %s";

	private RequestHelper requestHelper = RequestHelper.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

//	private void processRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String page = null;
//		try {
//			// request.getPathInfo();
//			LOGGER.info("IN Controller, get path info:" + request.getPathInfo());
//			Command command = requestHelper.getCommand(request);
//
//			page = command.execute(request, response);
//			LOGGER.debug(page);
//
//		} catch (ServletException e) {
//			e.printStackTrace();
//			LOGGER.error(messageManager.getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
//
//			request.setAttribute("errorMessage",
//					messageManager.getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
//
//			page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
//		} catch (IOException e) {
//			e.printStackTrace();
//			LOGGER.error(messageManager.getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
//			request.setAttribute("errorMessage",
//					messageManager.getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
//			page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
//		}
//
//		if (!page.equals("/home")) {
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//			dispatcher.forward(request, response);
//		} else {
//			response.sendRedirect(request.getContextPath() + "/home");
//		}
//	}
	
	/*************************************************/
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	Command command = requestHelper.getCommand(request);
        	String page = command.execute(request, response);
            dispatch(page, request, response);

        } catch (RuntimeException e) {
            logExceptionAndRedirectToErrorPage(request, response, e);
        }
    }

	private void dispatch(String page, HttpServletRequest request, HttpServletResponse response) {
		String[] viewNameParts = page.split(":");
		String dispatchType = viewNameParts[0];
		String viewName = viewNameParts[1];

		switch (dispatchType) {
			case "forward":
				performForward(viewName, request, response);
				break;
			case "redirect":
				performRedirect(viewName, request, response);
				break;
			case "noAction":
				break;
			default:
				throw new IllegalArgumentException(String.format(INCORRECT_THE_DISPATCH_TYPE, page));
		}
	}
	
	private void performForward(String viewName, HttpServletRequest request, HttpServletResponse response) {
        try {
       	
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
            dispatcher.forward(request, response);        

        } catch (ServletException | IOException e) {
            throw new DispatchException(String.format(DISPATCHING_TO_THE_VIEW_NAME, viewName), e);
        }
    }

	private void performRedirect(String viewName, HttpServletRequest request, HttpServletResponse response) {
        HttpUtils.sendRedirect(request, response, viewName);
    }
    
    private void logExceptionAndRedirectToErrorPage(HttpServletRequest request, HttpServletResponse response,
            RuntimeException e) {
		LOGGER.error(String.format(USER_ID_REQUEST_URI, 
				HttpUtils.getUserIdFromSession(request), request.getRequestURI()), e);	
		
		HttpUtils.sendRedirect(request, response, HttpUtils.getErrorViewName(e));
	}
}
