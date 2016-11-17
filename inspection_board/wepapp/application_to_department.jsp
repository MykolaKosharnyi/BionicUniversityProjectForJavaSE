<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>

<title>Application to departments</title>

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

.separate_line {
	border: 0;
	height: 1px;
	background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
	background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
	background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
	background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
	margin: 10px 0px;
}
</style>
</head>
<body>
	<h3>At this page you can add or delete your application to the department.</h3>

	<hr class="separate_line">

	<a href="<c:url value='/inspection_board/home' />">Go to home page</a>
	<a href="<c:url value='/inspection_board/logout' />">Log out</a>

</body>
</html>