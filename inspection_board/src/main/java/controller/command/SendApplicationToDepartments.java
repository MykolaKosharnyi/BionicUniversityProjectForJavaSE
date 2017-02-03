package controller.command;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.entity.Certificate;
import model.service.CertificateService;
import model.service.DepartmentService;
import model.service.SheetService;
import model.service.impl.CertificateServiceImpl;
import model.service.impl.DepartmentServiceImpl;
import model.service.impl.SheetServiceImpl;
import model.entity.Subject;

public class SendApplicationToDepartments implements Command {
	private DepartmentService departmentService = DepartmentServiceImpl.getInstance();
	private CertificateService certificateService = CertificateServiceImpl.getInstance();
	private SheetService sheetService = SheetServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = HttpUtils.getUserIdFromSession(request);
		
		request.setAttribute("userSubjects", getUserSubjects(id));
		request.setAttribute("userDepartments", sheetService.findByEnrolleeId(id));
		request.setAttribute("departments", departmentService.findAll());

		return FORWARD + configurationManger.getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PAGE);
	}
	
	private Set<Subject> getUserSubjects(long id){
		Certificate certificate = certificateService.find(id);
		return certificate.getItemsWithEstimates().keySet();
	}
}
