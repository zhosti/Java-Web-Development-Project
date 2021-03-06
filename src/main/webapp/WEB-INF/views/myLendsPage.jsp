<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Lends</title>
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
	        <th>Book Title</th>
	        <th>Author</th>
	        <th>Lend Date</th>
	        <th>Return Date</th>
	      </tr>
	    </thead>
	    <c:if test="${not empty users}">
	    <tbody>
	    <c:forEach var="u" items="${users}">
	      <tr>
	        <td>${u.username }</td>
	        <td>${u.bookName }</td>
	        <td>${u.author }</td>
   	        <td>${u.dateOfLending }</td>
   	        <td>${u.dateOfReturn }</td>
	      </tr>
	      </c:forEach>
	    </tbody>
	    </c:if>
	  </table>
  	</div>
	<ct:Footer></ct:Footer>
</body>
</html>