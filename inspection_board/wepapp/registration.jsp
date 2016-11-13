<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Registration page.</title>

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

	<form action="<c:url value='/registration' />" method="post">
		<table>
			<tr>
				<td>First name:</td>
				<td><input type="text" name="firstname" autofocus="autofocus"></td>
			</tr>
			<tr>
				<td>Second name:</td>
				<td><input type="text" name="secondname"></td>
			</tr>
			<tr>
				<td>E-mail:</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Phone number:</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td colspan="2"><hr style="border: 0; 
  height: 1px; 
  background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="password" name="repeat_password"></td>
			</tr>

			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Register new account"></td>
			</tr>
			<tr>
				<td colspan="2"><hr class="hr_end_block"></td>
			</tr>
		</table>
		 
	</form>

	<a href="<c:url value='/' />">Go to welcome page</a>

</body>
</html>