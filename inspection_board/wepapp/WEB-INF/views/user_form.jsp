<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>

<!-- INCLUDE HEAD OF PAGE -->
<jsp:include page="/WEB-INF/views/head.jsp" />

<c:if test="${empty user || user.id==0}">
	<title>Registration page</title>
</c:if>

<c:if test="${user.id!=0}">
	<title>Change user information</title>
</c:if>

</head>
<body>
	<c:if test="${!empty user && user.id!=0}">
		<jsp:include page="/WEB-INF/views/user_navigation.jsp" />
	</c:if>

<c:if test="${empty user || user.id==0}">
	<h3 class="text-center">At this page you can register your account</h3>
</c:if>
	
<c:if test="${!empty user && user.id!=0}">	
	<h3 <c:if test="${!empty user && user.id!=0}">style="padding-top: 70px;"</c:if>
		class="text-center">At this page you can change your personal information</h3>
</c:if>

	<c:if test="${empty user || user.id==0}">
		<c:url var="addAction" value="./registration_post"></c:url>
	</c:if>

	<c:if test="${!empty user && user.id!=0}">
		<c:url var="addAction" value="./user_edit_post"></c:url>
	</c:if>

	<form action="${addAction}" method="post">
		
		<c:if test="${!empty user && user.id!=0}">
			<input value="${user.id}" type="hidden" name="USER_ID">
		</c:if>
		
		<div class="form-group">
			<label for="firstName">First name</label> <input min="3" max="50"
				<c:if test="${!empty user.firstName}">value="<c:out value="${user.firstName}" />"</c:if> required
				type="text" class="form-control" name="firstname" id="firstName"
				placeholder="First name" autofocus="autofocus">
		</div>
		<c:if test="${!empty firstNameMessage}">
			<div class="errorMessage">${firstNameMessage}</div>					
		</c:if>
		
		<div class="form-group">
			<label for="secondName">Second name</label> <input min="3" max="50"
				<c:if test="${!empty user.secondName}">value="${fn:escapeXml(user.secondName)}"</c:if> required
				type="text" class="form-control" name="secondname" id="secondName"
				placeholder="Second name">
		</div>
		<c:if test="${!empty secondMessage}">
			<div class="errorMessage">${secondMessage}</div>					
		</c:if>
		
		<div class="form-group">
			<label for="inputEmail">Email address</label> <input
				<c:if test="${!empty user.email}">value="${user.email}"</c:if> required
				type="email" class="form-control" name="email" id="inputEmail"
				placeholder="Email">
		</div>
		<c:if test="${!empty emailMessage}">
			<div class="errorMessage">${emailMessage}</div>					
		</c:if>
	
		<div class="form-group">
			<label for="inputPhone">Phone number</label> <input
				<c:if test="${!empty user.phone}">value="${user.phone}"</c:if> required
				type="number" class="form-control" name="phone" id="inputPhone"
				placeholder="Phone number">
		</div>
		<c:if test="${!empty phoneMessage}">
			<div class="errorMessage">${phoneMessage}</div>					
		</c:if>
		
	
		<hr class="beauty_line">
		
		<div class="form-group">
			<label for="inputPassword">Password</label> <input
				<c:if test="${!empty user.password}">value="${user.password}"</c:if> required
				type="password" class="form-control" name="password" id="inputPassword"
				placeholder="Password">
		</div>
		
		<div class="form-group">
			<label for="inputPasswordRepeat">Repeat password</label> <input
				<c:if test="${!empty user.password}">value="${user.password}"</c:if> required
				type="password" class="form-control" name="repeat_password" id="inputPasswordRepeat"
				placeholder="Repeat password">
		</div>		
		<c:if test="${!empty passwordMessage}">
			<div class="errorMessage">${passwordMessage}</div>					
		</c:if>

		<c:if test="${empty user || user.id==0}">
			<input class="btn btn-default" type="submit" value="Register new account">
		</c:if>

		<c:if test="${!empty user && user.id!=0}">
			<input class="btn btn-default" type="submit" value="Change personal information">
		</c:if>

		<c:if test="${empty user || user.id==0}">
			<a class="btn btn-primary" href="<c:url value='/inspection_board/login' />">Log in</a>
			<a class="btn btn-primary" href="<c:url value='/' />">Go to welcome page</a>
		</c:if>

	</form>

</body>
</html>