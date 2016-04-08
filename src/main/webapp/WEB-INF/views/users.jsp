<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<ct:Header username="${username}" id="${id }"></ct:Header>
	
	<div class="container">
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th>Username</th>
	        <th>Status</th>
	        <th>Authority</th>
	        <th>PID</th>
	        <th>Birth Date</th>
	      </tr>
	    </thead>
	    <c:if test="${not empty users}">
	    <tbody>
	    <c:forEach var="u" items="${users}">
	      <tr>
	        <td>${u.username }</td>
	        
	        <c:choose>
				<c:when test="${u.getStatus() == 1}">
				    <td><span>Active</span></td>
			  	</c:when>
	           	<c:otherwise>
					<td><span>Not Active</span></td>
				</c:otherwise>
			</c:choose>
			<td>
	     		<c:forEach var="a" items="${u.getAuthorities()}">
					<p class="hidden-phone">${a.getAuthority()}</p>
				</c:forEach>
	        </td>
	        <td>${u.pid }</td>
	        <td>${u.birthDate }</td>
	        <td>
		        <form:form name="update" class="form-update" id="update-form" action="/libraries/adminEditUser/${u.getId()}" modelAttribute="users" method="GET">
					<button type="submit" class="btn btn-success">Update</button>
				</form:form>
	        </td>
	        <td>
		        <form:form name="delete" class="form-delete" id="update-form" action="/libraries/deleteProfile/${u.getId()}" modelAttribute="users" method="POST">
					<button type="submit" class="btn btn-danger">Delete</button>
				</form:form>
	        </td>
	      </tr>
	      </c:forEach>
	    </tbody>
	    </c:if>
	  </table>
		<form action="/libraries/addUser">
	  		<button type="submit" class="btn btn-primary">Add User</button>
		</form>
	</div>
	<ct:Footer></ct:Footer>
</body>
</html>