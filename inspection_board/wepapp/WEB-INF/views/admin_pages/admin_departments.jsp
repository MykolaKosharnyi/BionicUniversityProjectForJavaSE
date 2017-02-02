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
		class="text-center">At this page you can add or delete department</h3>

	<c:url var="addAction" value="./admin_add_department"></c:url>

	<hr class="beauty_line">

	<h4 class="text-center"><strong>Here you can add new department</strong></h4>

	<form action="${addAction}" class="form-horizontal" method="post">
	
		<div class="form-group">
			<label for="inputDepartmentName" class="col-sm-2 control-label">Department name</label> 
			<div class="col-sm-10">
		      <input type="text" id="inputDepartmentName" autofocus required name="departmentName">
		    </div>					
		</div>
		
		<div class="form-group">
			<label for="inputMaxAmountOfStudent" class="col-sm-2 control-label">Max amount of student</label> 
			<div class="col-sm-10">
		      <input type="text" id="inputMaxAmountOfStudent" required name="departmentMaxAmount">
		    </div>					
		</div>
		
		<div class="form-group">
			<label for="inputSubjects" class="col-sm-2 control-label">Required subjects</label> 
			<div class="col-sm-10">
				<select multiple="multiple" autofocus name="subjects" id="inputSubjects" required>
					<c:forEach items="${subjects}" var="subject">
						<option value="${subject.id}">${subject.name}</option>
					</c:forEach>
				</select>

		    </div>					
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Add department</button>
			</div>
		</div>

	</form>
	
	<hr class="beauty_line">

	<c:if test="${fn:length(departments) > 0}">
		
		<h4 class="text-center"><strong>Added subjects</strong></h4>
		<table class="table table-condensed table-bordered">
			<tr>
				<th class="text-center">#</th>
				<th class="text-center">Name of department</th>
				<th class="text-center">Max amount</th>
				<th class="text-center">Necessary items</th>
				
				<th></th>
			</tr>
			<c:forEach items="${departments}" var="entry" varStatus="loop">
				<tr>
					<td class="text-center">${loop.index + 1}</td>
					<td class="text-center">${entry.nameDepartment}</td>
					<td class="text-center">${entry.maxAmountStudent}</td>
					<td class="text-center">
					
					<c:set var="containtUserDepartment"
			value="${entry.necessaryItems.contains(subject)}" />
					
						<select multiple="multiple"
							name="subjects" id="inputSubjects" required>
								<c:forEach items="${subjects}" var="subject">
									<option 
										<c:if test="${entry.necessaryItems.contains(subject)}">selected="selected"</c:if>
										 value="${subject.id}">${subject.name}</option>
								</c:forEach>
						</select>
					</td>
					<td class="text-center">

						<form action="<c:url value='./admin_delete_department' />"
							method="post">
							<input type="hidden" name="departmentId" value="${entry.id}" />
							<input type="submit" value="delete" class="btn btn-danger">
						</form>

					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>