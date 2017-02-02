<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-default navbar-fixed-top" style="width:inherit; margin:inherit;">
  <div class="container-fluid">

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<li><a href="<c:url value='./admin_departments' />">Departments</a></li>
      	<li><a href="<c:url value='./admin_subjects' />">Subjects</a></li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value='./logout' />">Log out</a></li>
      </ul>
    </div>
  </div>
</nav>