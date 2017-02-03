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
	private SheetServiceImpl sheetService = SheetServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.setAttribute( "sheet", sheetService.getSheet() );
		request.setAttribute( ApplicationResources.CURRENT_USER_ATTR_NAME, getUser(request) );
		return FORWARD + configurationManger.getProperty(ConfigurationManager.SHEET_PAGE);
	}
	
	private User getUser(HttpServletRequest request){
		return HttpUtils.getCurrentUserFromFromDb(request);
	}

}
