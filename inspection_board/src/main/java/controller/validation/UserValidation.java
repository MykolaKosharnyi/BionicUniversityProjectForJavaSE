package controller.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import controller.ParameterContants;
import model.entity.User;

public class UserValidation {
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private User user;
	
	private Pattern pattern;
	private Matcher matcher;

	public UserValidation(){
		this(new User());
	}
	
	public UserValidation(User user){
		this.user=user;
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public boolean validate(HttpServletRequest request){
		String id = request.getParameter(ParameterContants.PARAM_USER_ID);
		String firstName = request.getParameter(ParameterContants.PARAM_FIRSTNAME);
		String secondName = request.getParameter(ParameterContants.PARAM_SECONDNAME);
		String email = request.getParameter(ParameterContants.PARAM_EMAIL);
		String phone = request.getParameter(ParameterContants.PARAM_PHONE);
		String password = request.getParameter(ParameterContants.PARAM_PASSWORD);
		String repeatPassword = request.getParameter(ParameterContants.PARAM_REPEAT_PASSWORD);
		
		checkId(id);
		
		boolean result = true;
		if(validFirstName(firstName)){
			user.setFirstName(firstName);
		} else {
			result = false;
			request.setAttribute("firstNameMessage", "Firstname must be min=3 sybols and max=50!");
		}
		
		if(validSecondName(secondName)){
			user.setSecondName(secondName);
		} else {
			result = false;
			request.setAttribute("secondMessage", "Firstname must be min=3 sybols and max=50!");
		}
		
		if(validEmail(email)){
			user.setEmail(email);
		} else {
			result = false;
			request.setAttribute("emailMessage", "Incorrect e-mail!");
		}
		
		if(validPhone(phone)){
			user.setPhone(phone);
		} else {
			result = false;
			request.setAttribute("phoneMessage", "Incorrect phone!");
		}
		
		if(validPassword(password, repeatPassword)){
			user.setPassword(password);
		} else {
			result = false;
			request.setAttribute("passwordMessage", "Passwords was different!");
		}
		
		return result;
	}
	
	private void checkId(String id){
		if(id!=null){
			user.setId(Long.parseLong(id));
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	private boolean validFirstName(String firstName){
		String result = firstName.trim();
		return result.length()>=3 && result.length()<=50;
	}
	
	private boolean validSecondName(String secondName){
		String result = secondName.trim();
		return result.length()>=3 && result.length()<=50;
	}
	
	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validEmail(final String hex) {
		String result = hex.trim();
		matcher = pattern.matcher(result);
		return matcher.matches();

	}
	
	private boolean validPhone(String phone){
		return phone.matches("^-?\\d+$");
	}
	
	private boolean validPassword(String password, String repeatPassword){
		String passwordT = password.trim();
		String repeatPasswordT = password.trim();
		return passwordT.equals(repeatPasswordT
				);
	}
}
