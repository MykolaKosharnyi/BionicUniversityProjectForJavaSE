<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-default navbar-fixed-top" style="width:inherit; margin:inherit;">
  <div class="container-fluid">

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<li><a href="<c:url value='./set_application_to_departments' />">Set application to departments</a></li>
        <li><a href="<c:url value='./add_or_change_subject' />">Add or change subjects</a></li>
        <li><a href="<c:url value='./user_edit' />">Change personal information</a></li>
		<li><a href="<c:url value='./position_in_ratings' />">Show your position in ratings</a></li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value='./logout' />">Log out</a></li>
      </ul>
    </div>
  </div>
</nav>