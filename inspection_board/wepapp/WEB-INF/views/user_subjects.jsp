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
	
	<jsp:include page="/WEB-INF/views/user_navigation.jsp" />

	<h3 style="padding-top: 70px;"
		class="text-center">At this page you can add, delete or change your subjects</h3>

	<c:url var="addAction" value="./add_or_change_post_subject"></c:url>

	<hr class="beauty_line">

	<h4 class="text-center"><strong>Here you can add new subject</strong></h4>

	<form action="${addAction}" class="form-horizontal" method="post">
		<div class="form-group">
			<label for="inputSubject" class="col-sm-2 control-label">Subject</label> 				
			<div class="col-sm-10">
		      <select autofocus name="subjectId" id="inputSubject" required>
				<c:forEach items="${subjects}" var="subject">
					<option value="${subject.id}">${subject.name}</option>
				</c:forEach>
			  </select>
		    </div>		
		</div>
		
		<div class="form-group">
			<label for="inputScope" class="col-sm-2 control-label">Scope</label> 
			<div class="col-sm-10">
		      <input type="number" min="1" max="12" id="inputScope" required name="scope">
		    </div>					
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Add subject</button>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-11">
				<blockquote>
				  <footer><div style="color:red; float:left;">*&emsp;</div>
				  If you already added subject with scope, you need first delete exist subject!</footer>
				</blockquote>
			</div>
		</div>

	</form>
	
	<hr class="beauty_line">

	<c:if test="${fn:length(user_subjects.itemsWithEstimates) > 0}">
		
		<h4 class="text-center"><strong>Added subjects</strong></h4>
		<table class="table table-condensed table-bordered">
			<tr>
				<th class="text-center">#</th>
				<th class="text-center">Name of subject</th>
				<th class="text-center">Scope</th>
				<th></th>
			</tr>
			<c:forEach items="${user_subjects.itemsWithEstimates}" var="entry"
				varStatus="loop">
				<tr>
					<td class="text-center">${loop.index + 1}</td>
					<td class="text-center">${entry.key.name}</td>
					<td class="text-center">${entry.value}</td>
					<td class="text-center">

						<form action="<c:url value='./delete_user_subject' />"
							method="post">
							<input type="hidden" name="subjectId" value="${entry.key.id}" />
							<input type="submit" value="delete" class="btn btn-danger">
						</form>

					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4"><strong>Average scope: ${user_subjects.getAverageRating() }</strong></td>
			</tr>
		</table>

	</c:if>

</body>
</html>