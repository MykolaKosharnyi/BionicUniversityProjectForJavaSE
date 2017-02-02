<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

<!-- INCLUDE HEAD OF PAGE -->
<jsp:include page="/WEB-INF/views/head.jsp" />
<title>Application to departments</title>

</head>
<body>

	<jsp:include page="/WEB-INF/views/user_navigation.jsp" />


	<h3 style="padding-top: 70px;" 
	class="text-center">At this page you can see all send users application to all departments</h3>
	<h4 class="bg-warning" style="padding: 10px; border-radius: 5px;width: 50%;border: 1px solid black;">Available number of places at the Faculty</h4>
	<c:if test="${!empty user}">
		<h4 class="bg-success" style="padding: 10px; border-radius: 5px;width: 50%;border: 1px solid black;margin-bottom: 50px;">Your position in the rating</h4>
	</c:if>
	

	<c:forEach items="${sheet.table}" var="item_departmnet" varStatus="loop">
	
		<h4 class="text-center"><em><strong>${item_departmnet.key.nameDepartment}</strong></em></h4>
		<table class="table table-condensed table-bordered" style="margin-bottom: 45px;">
		  	<tr>
				<th class="text-center">#</th>
				<th class="text-center">First name</th>
				<th class="text-center">Second name</th>
				<th class="text-center">Average rating</th>
			</tr>
			
			<c:forEach items="${item_departmnet.value}" var="item" varStatus="loop">
				<tr class="<c:if test="${user.id == item.id}">success</c:if>
				<c:if test="${((item_departmnet.key.maxAmountStudent-(loop.index+1)) > 0)&&(user.id != item.id)}">warning</c:if>">
					<td class="text-center">${loop.index + 1}</td>
					<td class="text-center"><c:out value="${item.firstName}" /></td>
					<td class="text-center"><c:out value="${item.secondName}" /></td>
					<td class="text-center"><fmt:formatNumber type="number" maxFractionDigits="2"
					 minFractionDigits="2" value="${item.certificate.getAverageRating()}" />
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:forEach>

</body>
</html>