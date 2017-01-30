package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	String FORWARD = "forward:";
    String REDIRECT = "redirect:";
    String NO_ACTION = "noAction:noUri";
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
