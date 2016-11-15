<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<c:if test="${empty user}">
	<title>Registration page</title>
</c:if>

<c:if test="${!empty user}">
	<title>Change user information</title>
</c:if>

<style>
body {
	width: 900px;
	margin: 0 auto;
}

h3 {
	margin: 50px 0;
}

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
	<h3>At this page you can register your account</h3>

	<c:if test="${empty user}">
		<c:url var="addAction" value="/registration"></c:url>
	</c:if>

	<c:if test="${!empty user}">
		<c:url var="addAction" value="/user_edit"></c:url>
	</c:if>

	<form action="${addAction}" method="post">
		<table>
			<tr>
				<td>First name:</td>
				<td><input type="text" required name="firstname"
					<c:if test="${!empty user}">value="${user.firstName}"</c:if>
					autofocus="autofocus"></td>
			</tr>
			<tr>
				<td>Second name:</td>
				<td><input type="text" required name="secondname"
					<c:if test="${!empty user}">value="${user.secondName}"</c:if>></td>
			</tr>
			<tr>
				<td>E-mail:</td>
				<td><input type="email" required name="email"
					<c:if test="${!empty user}">value="${user.email}"</c:if>></td>
			</tr>
			<tr>
				<td>Phone number:</td>
				<td><input type="number" required name="phone"
					<c:if test="${!empty user}">value="${user.phone}"</c:if>></td>
			</tr>
			<tr>
				<td colspan="2"><hr
						style="border: 0; height: 1px; background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0); background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0); background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0); background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" required name="password"
					<c:if test="${!empty user}">value="${user.password}"</c:if>></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="password" required name="repeat_password"
					<c:if test="${!empty user}">value="${user.password}"</c:if>></td>
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
				<td></td>
				<td></td>
			</tr>

			<c:if test="${empty user}">
				<tr>
					<td colspan="2"><input type="submit"
						value="Register new account"></td>
				</tr>
			</c:if>

			<c:if test="${!empty user}">
				<tr>
					<td colspan="2"><input type="submit"
						value="Change personal information"></td>
				</tr>
			</c:if>

			<tr>
				<td colspan="2"><hr class="hr_end_block"></td>
			</tr>
		</table>

	</form>

	<c:if test="${empty user}">
		<a href="<c:url value='/' />">Go to welcome page</a>
	</c:if>

	<c:if test="${!empty user}">
		<a href="<c:url value='/home' />">Go to home page</a>
		<a href="<c:url value='/LogoutServlet' />">Log out</a>
	</c:if>


</body>
</html>