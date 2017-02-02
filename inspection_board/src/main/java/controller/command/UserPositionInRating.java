package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ApplicationResources;
import controller.ConfigurationManager;
import controller.HttpUtils;
import model.entity.User;
import model.service.impl.SheetServiceImpl;

public class UserPositionInRating implements Command {
	SheetServiceImpl sheetService = SheetServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.setAttribute( "sheet", sheetService.getSheet() );
		request.setAttribute( ApplicationResources.CURRENT_USER_ATTR_NAME, getUser(request) );
		return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHEET_PAGE);
	}
	
	private User getUser(HttpServletRequest request){
		return HttpUtils.getCurrentUserFromFromDb(request);
	}

}
