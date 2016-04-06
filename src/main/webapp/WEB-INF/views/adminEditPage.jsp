<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty user}">
		<form:form name="edit" class="form-edit" id="edit-form" action="/libraries/editUser/${user.getId()}" modelAttribute="users" method="POST">
	        <h2 class="form-signin-heading">Edit user</h2>
	        <label for="username" class="sr-only">Username</label>
	        <input type="text" id="username" name="username" class="form-control" placeholder="${user.getUsername()}" readonly>	      
	        <label for="pid" class="sr-only">PID</label>
	        <input type="text" id="pid" name="pid" class="form-control" placeholder="${user.getPid()}" readonly>
			<select class="form-control" name="status_select" id="status_select">
			<c:choose>
				<c:when test="${user.getStatus() == 1}">
				    <option value="0">Inactiv</option>
		  			<option value="1" selected="selected">Active</option>
			  	</c:when>
               	<c:otherwise>
						<option value="0" selected="selected">Inactiv</option>
		  				<option value="1">Active</option>
				</c:otherwise>
			</c:choose>
			</select>
	        
	        <input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="Edit" />
			<a class="btn btn-default btn-block" href="/library/users/all/">Cancel</a>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      	</form:form>
   	</c:if>
</body>
</html>