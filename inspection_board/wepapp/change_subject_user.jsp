<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>

<title>Change subjects</title>

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

.separate_line{
border: 0; 
height: 1px; 
background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0); 
background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0); 
background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0); 
background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
margin:10px 0px;
}
</style>
</head>
<body>
	<h3>At this page you can add, delete or change your subjects</h3>

	<c:url var="addAction" value="./add_or_change_post_subject"></c:url>

	<form action="${addAction}" method="post">
		<table>
			<tr>
				<th>Subject</th>
				<th>Scope</th>
				<th></th>
				<th></th>
			</tr>
			
			<tr>
				<td>
					<select autofocus name="subject" required>
						<c:forEach items="${subjects}" var="subject">  
							<option value="${subject.id}">${subject.name}</option>  
						</c:forEach> 
					</select>
				</td>
				<td><input type="number" min="1" max="12" required name="scope"></td>
				<td><input type="submit" value="Add subject"></td>
				<td>(If you already added subject with scope, you need first delete exist subject!)</td>
			</tr>

		</table>

	</form>


	<c:if test="${fn:length(user_subjects.itemsWithEstimates) > 0}">
		<hr class="separate_line">
		
		<h4>Added subjects:</h4>
		<table>
			<c:forEach items="${user_subjects.itemsWithEstimates}" var="entry" varStatus="loop">
				<tr>
					<td>${loop.index + 1})</td>
					<td>${entry.key.name}</td>
					<td>${entry.value}</td>
					<td>

						<form action="<c:url value='./delete_user_subject' />" method="post">
							<input type="hidden" name="subjectId" value="${entry.key.id}" /> 
							<input type="submit" value="delete"
								style="position: relative; top: 7px; left: 20px;">
						</form>

					</td>
				</tr>
			</c:forEach>	
			<tr>
				<td colspan="4"><hr></td>
			</tr>
			<tr>
				<td></td>
				<td>Average scope:</td>
				<td colspan="2">${user_subjects.getAverageRating() }</td>
			</tr>
		</table>
		
		<hr class="separate_line">
	</c:if>

	<a href="<c:url value='./home' />">Go to home page</a>
		<a href="<c:url value='./logout' />">Log out</a>

</body>
</html>