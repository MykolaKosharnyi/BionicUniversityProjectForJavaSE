<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>

<!-- INCLUDE HEAD OF PAGE -->
<jsp:include page="/WEB-INF/views/head.jsp" />
<title>Change subjects</title>

</head>
<body>
	
	<jsp:include page="/WEB-INF/views/admin_pages/admin_navigation.jsp" />

	<h3 style="padding-top: 70px;"
		class="text-center">At this page you can add or delete subjects</h3>

	<c:url var="addAction" value="./admin_add_subject"></c:url>

	<hr class="beauty_line">

	<h4 class="text-center"><strong>Here you can add new subject</strong></h4>

	<form action="${addAction}" class="form-horizontal" method="post">
	
		<div class="form-group">
			<label for="inputSubjectName" class="col-sm-2 control-label">Subject name</label> 
			<div class="col-sm-10">
		      <input type="text" id="inputSubjectName" autofocus required name="subjectName">
		    </div>					
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Add subject</button>
			</div>
		</div>

	</form>
	
	<hr class="beauty_line">

	<c:if test="${fn:length(subjects) > 0}">
		
		<h4 class="text-center"><strong>Added subjects</strong></h4>
		<table class="table table-condensed table-bordered">
			<tr>
				<th class="text-center">#</th>
				<th class="text-center">Name of subject</th>
				<th></th>
			</tr>
			<c:forEach items="${subjects}" var="entry" varStatus="loop">
				<tr>
					<td class="text-center">${loop.index + 1}</td>
					<td class="text-center">${entry.name}</td>
					<td class="text-center">

						<form action="<c:url value='./admin_delete_subject' />"
							method="post">
							<input type="hidden" name="subjectId" value="${entry.id}" />
							<input type="submit" value="delete" class="btn btn-danger">
						</form>

					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>