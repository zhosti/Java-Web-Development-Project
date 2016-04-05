<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${not empty book}">
		<form:form name="update" class="form-update" id="update-form" action="/libraries/edit/${book.getId()}" modelAttribute="book" method="POST">
	        <h2 class="form-signin-heading">Update book</h2>
	        <label for="book-name" class="sr-only">Book name</label>
	        <input type="text" id="book-name" name="book-name" class="form-control" placeholder="${book.getName()}">
	        <label for="author" class="sr-only">Name</label>
	        <input type="text" id="author" name="author" class="form-control" placeholder="${book.getAuthor()}">
			<label for="Year" class="sr-only">Year of publishing</label>
	        <input type="date" id="year" name="year" value="YYYY/mm/dd"class="form-control" placeholder="${book.getYearOfPublishing()}">
			
	        <input class="btn btn-danger btn-block" name="submit" type="submit" value="Update" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      	</form:form>
   	</c:if>
</body>
</html>