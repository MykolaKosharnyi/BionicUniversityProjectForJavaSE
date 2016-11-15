<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Welcome page</title>

<style>
body{
width:900px; margin: 0 auto;
}

h3{margin: 50px 0;}

a { 
	background: greenyellow;
	border-radius: 5px;
	padding: 5px;
	margin: 0 10px;
	color: #872c1e;
}

</style>
</head>
<body>
	<h3>
		Here you can log in
	</h3>

	<form method="post" action="controller">
	
		<input type="hidden" name="command" value="login" /> 
		<table>
			<tr>
				<td>E-mail:</td>
				<td><input type="text" name="email"></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"></td>
			</tr>
			
			<c:if test="${!empty errorMessage}">
				<tr>
					<td colspan="2">
						<div
							style="color: red; border: 1px solid red; padding: 5px; margin: 10px 0px;">${errorMessage}</div>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="2"><input type="submit" value="Log in"></td>
			</tr>
		</table>
		
	</form>

	<a href="<c:url value='/' />">Go to home page</a><a href="<c:url value='/registration' />">Registration</a>

</body>
</html>