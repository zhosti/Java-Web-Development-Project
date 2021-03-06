<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

	<c:if test="${not empty book && not empty lendBook}">
		<form:form name="edit" class="form-edit" id="edit-form" action="/libraries/editLend/${book.getId()}/${lendBook.getId()}" method="POST">
	        <h2 class="form-signin-heading">Edit lend</h2>
	        <label for="name">Book Title</label>
	        <input type="text" id="name" name="name" class="form-control" value="${book.getName()}" readonly>
	        <label for="author">Author</label>
	        <input type="text" id="author" name="author" class="form-control" value="${book.getAuthor()}" readonly>
	        <label for="dateOfLending">Date of lending</label>
	        <input type="date" id="lend-date" name="lend-date" class="form-control" value="${lendBook.getDateOfLending()}" readonly>
	        <label for="dateOfReturn">Date of return</label>
	        <input type="date" id="return-date" name="return-date" class="form-control" value="${lendBook.getDateOfReturn()}" >
	        
	        <input class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="Edit" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      	</form:form>
  	</c:if>
  	<ct:Footer></ct:Footer>
</body>
</html>