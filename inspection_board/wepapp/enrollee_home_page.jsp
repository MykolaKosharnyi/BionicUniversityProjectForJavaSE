<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home page</title>

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
	padding: 10px;
	margin: 0 10px;
	color: #872c1e;
	text-decoration: none;
}

table {
margin-bottom: 40px;
}

table a {
display: block;
background: #affcf4;
display: block;
color: #7b5c12;
padding: 20px;
margin: 5px;
}

table a:hover {
display: block;
background:#7b5c12 ;
display: block;
color: #affcf4;
}
</style>
</head>
<body>
	<h3>At this page you can change your personal information, add subject to your certificate, add application to departments.</h3>
	
	<table>
		<tr>
			<td><a href="<c:url value='/user_edit' />">Change personal information</a></td>
			<td><a href="<c:url value='/add_or_change_subject' />">Add or change subjects</a></td>
		</tr>
		<tr>
			<td><a href="<c:url value='/set_application_to_departments' />">Set application to departments.</a></td>
			<td><a href="<c:url value='/show_your_posion_in_ratings' />">Show your position in ratings.</a></td>
		</tr>

	</table>
	
	
	
	
	
	

	<a href="<c:url value='/' />">Go to welcome page</a> <a href="<c:url value='/LogoutServlet' />">Log out</a>

</body>
</html>