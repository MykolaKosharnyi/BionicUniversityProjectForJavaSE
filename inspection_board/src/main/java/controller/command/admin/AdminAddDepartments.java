package controller.command.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConfigurationManager;
import controller.command.Command;
import model.entity.Department;
import model.entity.Subject;
import model.service.DepartmentService;
import model.service.SubjectService;
import model.service.impl.DepartmentServiceImpl;
import model.service.impl.SubjectServiceImpl;

public class AdminAddDepartments implements Command {
	private DepartmentService departmentService = DepartmentServiceImpl.getInstance();
	private SubjectService subjectService = SubjectServiceImpl.getInstance();
	private ConfigurationManager configurationManger = ConfigurationManager.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		departmentService.create( getObjectFromRequest(request) );
		return REDIRECT + "admin_departments"
				/*configurationManger.getProperty(ConfigurationManager.DEPARTMENTS_ADMIN_PATH)*/;
	}

	private Department getObjectFromRequest(HttpServletRequest request){
		 return new Department.Builder()
					.setNameDepartment(getNameOfDepartmentFromRequest(request))
					.setMaxAmountStudent(getMaxEnrolleeFromRequest(request))
					.setNecessaryItems(getSubjectFromRequest(request))
					.build();
	}
	
	private String getNameOfDepartmentFromRequest(HttpServletRequest request){
		return request.getParameter("departmentName");
	}
	
	private int getMaxEnrolleeFromRequest(HttpServletRequest request){
		return Integer.parseInt(request.getParameter("departmentMaxAmount"));
	}
	
	private List<Subject> getSubjectFromRequest(HttpServletRequest request){
		List<Subject> result = new ArrayList<>();
		String[] subjects=request.getParameterValues("subjects");
		
		for(String item : subjects){
			result.add(getSubjectFromService(item));
		}		
		return result;
	}
	
	private Subject getSubjectFromService(String item){
		return subjectService.find(Integer.parseInt(item)).get();
	}
	
}
