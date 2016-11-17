package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import controller.command.Command;

public class Controller extends HttpServlet implements javax.servlet.Servlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(Controller.class);

	RequestHelper requestHelper = RequestHelper.getInstance();

//	public void init(){
//		new DOMConfigurator().doConfigure("C:/Users/Íèêîëàé/Desktop/workspaceLinux/_Four_Project/src/log4j.xml", LogManager.getLoggerRepository());
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String page = null;
		try {
			//request.getPathInfo();
			logger.info("IN Controller, get path info:" + request.getPathInfo());
			Command command = requestHelper.getCommand(request);

			page = command.execute(request, response);
			logger.debug(page);
			
		} catch (ServletException e) {
			e.printStackTrace();
			logger.error( MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
			
			request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
			
			page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
			request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
		}
		
		if(!page.equals("/home")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}
