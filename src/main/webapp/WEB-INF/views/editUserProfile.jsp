<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

	<c:if test="${not empty user}">
		<form:form name="update" class="form-update" id="update-form" action="/libraries/editUserProfile/${user.getId()}" modelAttribute="book" method="POST">
	        <h2 class="form-signin-heading">Update profile</h2>
	        <label for="username" class="sr-only">Username</label>
	        <input type="text" id="username" name="username" class="form-control" placeholder="${user.getUsername()}">
	        <label for="password" class="sr-only">Password</label>
	        <input type="password" id="password" name="password" class="form-control">
			<label for="date-birth" class="sr-only">Date Birth</label>
	        <input type="date" id="date-birth" name="date-birth" value="YYYY/mm/dd"class="form-control" placeholder="${user.getBirthDate()}">
			
	        <input class="btn btn-danger btn-block" name="submit" type="submit" value="Update" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      	</form:form>
   	</c:if>
   	<ct:Footer></ct:Footer>
</body>
</html>