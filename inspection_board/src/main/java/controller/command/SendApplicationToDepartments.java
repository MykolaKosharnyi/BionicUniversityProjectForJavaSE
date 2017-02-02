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
	DepartmentService departmentService = DepartmentServiceImpl.getInstance();
	CertificateService certificateService = CertificateServiceImpl.getInstance();
	SheetService sheetService = SheetServiceImpl.getInstance();
	ConfigurationManager configurationManger = ConfigurationManager.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = HttpUtils.getUserIdFromSession(request);
		
		Certificate certificate = certificateService.find(id);
		Set<Subject> userSubjects = certificate.getItemsWithEstimates().keySet();
		
		request.setAttribute("userSubjects", userSubjects);
		request.setAttribute("userDepartments", sheetService.findByEnrolleeId(id));
		request.setAttribute("departments", departmentService.findAll());

		return FORWARD + configurationManger.getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PAGE);
	}
}
