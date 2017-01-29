package controller.command;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConfigurationManager;
import model.service.CertificateService;
import model.service.DepartmentService;
import model.entity.Certificate;
import model.entity.Department;
import model.entity.User;
import model.entity.Sheet;
import model.entity.Subject;

public class ApplicationToDepartments implements Command {
	DepartmentService departmentService = DepartmentService.getInstance();
	CertificateService certificateService = CertificateService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get all departments
		request.setAttribute("departments", departmentService.findAll());

		HttpSession session = request.getSession();
		long id = (long) session.getAttribute("userId");

		// get certificate for this user
		Certificate certificate = certificateService.find(id);
		Set<Subject> userSubjects = certificate.getItemsWithEstimates().keySet();
		request.setAttribute("userSubjects", userSubjects);

		// get Departments where user send application
		Sheet sheet = new Sheet();
		Set<Department> departmentList = sheet.getSheet().keySet();

		Iterator<Department> departmentIterator = departmentList.iterator();

		while (departmentIterator.hasNext()) {
			Department currentDepartment = departmentIterator.next();
			boolean isNeededDepartment = false;

			List<User> listEnrollee = sheet.getSheet().get(currentDepartment);
			Optional<User> enrolleeOptional = listEnrollee.stream().filter(e -> e.getId() == id).findFirst();
			if (enrolleeOptional.isPresent()) {
				isNeededDepartment = true;
			}

			if (!isNeededDepartment) {
				departmentIterator.remove();
			}
		}

		request.setAttribute("userDepartments", departmentList);

		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_APPLICATION_TO_DEPARTMENT_PAGE);
	}
}
