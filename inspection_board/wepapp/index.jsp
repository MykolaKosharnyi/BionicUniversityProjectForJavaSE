<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<!-- INCLUDE HEAD OF PAGE -->
<jsp:include page="/WEB-INF/views/head.jsp" />

<title>Welcome page</title>
<style>

</style>
</head>
<body>

	<h1 class="text-center">Inspection board system</h1>
	<h3 class="text-center">You can leave here your an application for admission to the
		Faculty</h3>

	<div class="text-center">
		<a class="btn btn-primary" href="<c:url value='/inspection_board/login' />">Log in</a>
		<a class="btn btn-primary" href="<c:url value='/inspection_board/registration' />">Registration</a>
	</div>

</body>
</html>