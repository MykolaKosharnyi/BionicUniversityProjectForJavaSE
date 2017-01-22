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
		You can leave here your an application for admission to the Faculty.
	</h3>
	
	<a href="<c:url value='./login' />">Log in</a><a href="<c:url value='./registration' />">Registration</a>

</body>
</html>