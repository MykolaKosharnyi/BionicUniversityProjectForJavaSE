package controller.command;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.HttpUtils;
import model.entity.Certificate;
import model.entity.Department;
import model.entity.User;
import model.service.impl.CertificateServiceImpl;
import model.service.impl.DepartmentServiceImpl;
import model.service.impl.SheetServiceImpl;
import model.entity.Sheet;
import model.entity.Subject;

public class SendApplicationToDepartments implements Command {
	DepartmentServiceImpl departmentService = DepartmentServiceImpl.getInstance();
	CertificateServiceImpl certificateService = CertificateServiceImpl.getInstance();
	SheetServiceImpl sheetService = SheetServiceImpl.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get all departments
		request.setAttribute("departments", departmentService.findAll());

		long id = HttpUtils.getUserIdFromSession(request);

		// get certificate for this user
		Certificate certificate = certificateService.find(id);
		Set<Subject> userSubjects = certificate.getItemsWithEstimates().keySet();
		request.setAttribute("userSubjects", userSubjects);

		// get Departments where user send application
		//TODO use departmentService
		Sheet sheet = sheetService.getSheet();
		Set<Department> departmentList = sheet.getTable().keySet();

		Iterator<Department> departmentIterator = departmentList.iterator();

		while (departmentIterator.hasNext()) {
			Department currentDepartment = departmentIterator.next();
			boolean isNeededDepartment = false;

			List<User> listEnrollee = sheet.getTable().get(currentDepartment);
			
			Optional<User> enrolleeOptional = listEnrollee.stream().filter(e -> e.getId() == id).findFirst();
			if (enrolleeOptional.isPresent()) {
				isNeededDepartment = true;
			}

			if (!isNeededDepartment) {
				departmentIterator.remove();
			}
		}

		request.setAttribute("userDepartments", departmentList);

		return FORWARD + ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PAGE);
	}
}
