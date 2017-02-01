<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<!-- INCLUDE HEAD OF PAGE -->
<jsp:include page="/WEB-INF/views/head.jsp" />

<title>Login page</title>

</head>
<body>
	<h3 class="text-center">Here you can log in</h3>

	<form method="post" action="<c:url value='./login_post' />">
		<div class="form-group">
			<label for="inputEmail">Email address</label> <input required
				type="email" class="form-control" name="email" id="inputEmail"
				placeholder="Email">
		</div>
		<div class="form-group">
			<label for="inputPassword">Password</label> <input required
				type="password" class="form-control" name="password" id="inputPassword"
				placeholder="Password">
		</div>

		<c:if test="${!empty errorMessage}">
			<div class="form-group">
				<div class="errorMessage">${errorMessage}</div>
			</div>
		</c:if>
 
		<input class="btn btn-default" type="submit" value="Log in">

		<a class="btn btn-primary" href="<c:url value='./registration' />">Registration</a>
		
		<a class="btn btn-primary" href="<c:url value='/' />">Go to home page</a>

	</form>

</body>
</html>