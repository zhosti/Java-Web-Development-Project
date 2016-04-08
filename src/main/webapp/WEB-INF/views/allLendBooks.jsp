<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
 	<c:choose>
   		<c:when test="${not empty lendBooks}">	
		   <table class="table table-striped">
				<thead>
					<tr>
						<th>Username</th>
						<th>Book Title</th>
						<th>Author</th>
						<th>Date of lending</th>
						<th>Date of return</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
			        <c:forEach var="lb" items="${lendBooks}">
				        <tr>   
				        	<td>${lb.getUsername()}</td>
				        	<td>${lb.getBookName()}</td>
							<td>${lb.getAuthor()}</td>
							<td>${lb.getDateOfLending()}</td>
							<td>${lb.getDateOfReturn()}</td>
							
							<td>
								<form action="/libraries/editBookLend/${lb.getBookId()}/${lb.getId()}">
									<input type="submit" class="btn-primary btn mini red-stripe" value="Edit">
								</form>
							</td>				
 			                </tr>
			        </c:forEach>
	           </tbody>
	  		</table>
		</c:when>
		<c:otherwise>
			<h1>No lend books!</h1>
		</c:otherwise>
	</c:choose>
	<ct:Footer></ct:Footer>
</body>
</html>