<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<!-- INCLUDE HEAD OF PAGE -->
<jsp:include page="/WEB-INF/views/head.jsp" />

<c:if test="${empty user}">
	<title>Registration page</title>
</c:if>

<c:if test="${!empty user}">
	<title>Change user information</title>
</c:if>

</head>
<body>
	<c:if test="${!empty user}">
		<jsp:include page="/WEB-INF/views/user_navigation.jsp" />
	</c:if>

	<h3 <c:if test="${!empty user}">style="padding-top: 70px;"</c:if>
		class="text-center">At this page you can register your account</h3>

	<c:if test="${empty user}">
		<c:url var="addAction" value="./registration_post"></c:url>
	</c:if>

	<c:if test="${!empty user}">
		<c:url var="addAction" value="./user_edit_post"></c:url>
	</c:if>

	<form action="${addAction}" method="post">
		<div class="form-group">
			<label for="firstName">First name</label> <input
				<c:if test="${!empty user}">value="${user.firstName}"</c:if> required
				type="text" class="form-control" name="firstname" id="firstName"
				placeholder="First name" autofocus="autofocus">
		</div>
		
		<div class="form-group">
			<label for="secondName">Second name</label> <input
				<c:if test="${!empty user}">value="${user.secondName}"</c:if> required
				type="text" class="form-control" name="secondname" id="secondName"
				placeholder="Second name">
		</div>
		
		<div class="form-group">
			<label for="inputEmail">Email address</label> <input
				<c:if test="${!empty user}">value="${user.email}"</c:if> required
				type="email" class="form-control" name="email" id="inputEmail"
				placeholder="Email">
		</div>
	
		<div class="form-group">
			<label for="inputPhone">Phone number</label> <input
				<c:if test="${!empty user}">value="${user.phone}"</c:if> required
				type="number" class="form-control" name="phone" id="inputPhone"
				placeholder="Phone number">
		</div>
	
		<hr class="beauty_line">
		
		<div class="form-group">
			<label for="inputPassword">Password</label> <input
				<c:if test="${!empty user}">value="${user.password}"</c:if> required
				type="password" class="form-control" name="password" id="inputPassword"
				placeholder="Password">
		</div>
		
		<div class="form-group">
			<label for="inputPasswordRepeat">Repeat password</label> <input
				<c:if test="${!empty user}">value="${user.password}"</c:if> required
				type="password" class="form-control" name="repeat_password" id="inputPasswordRepeat"
				placeholder="Repeat password">
		</div>
		
		<c:if test="${!empty errorMessage}">
			<div class="errorMessage">${errorMessage}</div>					
		</c:if>

		<c:if test="${empty user}">
			<input class="btn btn-default" type="submit" value="Register new account">
		</c:if>

		<c:if test="${!empty user}">
			<input class="btn btn-default" type="submit" value="Change personal information">
		</c:if>

		<c:if test="${empty user}">
			<a class="btn btn-primary" href="<c:url value='/inspection_board/login' />">Log in</a>
			<a class="btn btn-primary" href="<c:url value='/' />">Go to welcome page</a>
		</c:if>

	</form>

</body>
</html>