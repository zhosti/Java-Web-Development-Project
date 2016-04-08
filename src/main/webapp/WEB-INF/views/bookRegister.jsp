<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
		#greeting{
			position: relative;
			left: 60em;
		}
		#logout{
			position: relative;
			left: 60em;
		}
</style>
</head>
<body>
	<ct:Header username="${username}" id="${id }"></ct:Header>
	
	<div class="container">
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th>Name</th>
	        <th>Author</th>
	        <th>Year of publishing</th>
	      </tr>
	    </thead>
	    <c:if test="${not empty books}">
	    <tbody>
	    <c:forEach var="b" items="${books}">
	      <tr>
	        <td>${b.name }</td>
	        <td>${b.author }</td>
	        <td>${b.yearOfPublishing }</td>
	        <td>
	        <c:forEach var="a" items="${user.getAuthorities()}">
	        	<c:choose>
					<c:when test="${a.getAuthority() == 'ROLE_ADMIN' }">
						<form:form name="update" class="form-update" id="update-form" action="/libraries/editBook/${b.getId()}" modelAttribute="books" method="GET">
							<button type="submit" class="btn btn-success">Update</button>
						</form:form>
	        		</c:when>
				</c:choose>
			</c:forEach>	
		        
	        </td>
	        <td>
	         <c:forEach var="a" items="${user.getAuthorities()}">
	        	<c:choose>
					<c:when test="${a.getAuthority() == 'ROLE_ADMIN' }">
						<form:form name="delete" class="form-delete" id="update-form" action="/libraries/delete/${b.getId()}" modelAttribute="books" method="POST">
						<button type="submit" class="btn btn-danger">Delete</button>
					</form:form>
	        		</c:when>
				</c:choose>
			</c:forEach>	    
	        </td>
	        <td>
	        	<form action="/libraries/lendBookPage/${b.getId() }">
				  		<button type="submit" class="btn btn-primary">Lend Book</button>
				</form>
	        </td>
	      </tr>
	      </c:forEach>
	    </tbody>
	    </c:if>
	  </table>
	  <c:forEach var="a" items="${user.getAuthorities()}">
	       	<c:choose>
				<c:when test="${a.getAuthority() == 'ROLE_ADMIN' }">
					<form action="/libraries/addBook">
				  		<button type="submit" class="btn btn-primary">Add Book</button>
					</form>
	       		</c:when>
			</c:choose>
	  </c:forEach>	
	</div>
	<ct:Footer></ct:Footer>
</body>
</html>