<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
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
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/home">Library</a>
    </div>
	
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/home">Home <span class="sr-only">(current)</span></a></li>
        <li><a href="/libraries/books">Books</a></li>
        <li><a href="/libraries/users">Users</a></li>
        <li id="greeting">
       		<a href="/libraries/editProfile/${id }">Hello, <c:out value="${username}" /></a>
        <li>
        <li id="logout"><a href="logout">Logout</a>
      </ul>  
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
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
</body>
</html>