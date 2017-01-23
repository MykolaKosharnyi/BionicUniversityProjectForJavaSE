package controller.command;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.service.CertificateService;
import model.service.DepartmentService;
import model.entity.Certificate;
import model.entity.Subject;

public class ApplicationToDepartments implements Command {
	DepartmentService departmentService = DepartmentService.getInstance();
	CertificateService certificateService = CertificateService.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("departments", departmentService.findAll());
		
		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");
		
		Certificate certificate = certificateService.find(id);
		Set<Subject> userSubjects = certificate.getItemsWithEstimates().keySet();
		request.setAttribute("userSubjects", userSubjects);
		
		
		
		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PAGE);
	}
}
