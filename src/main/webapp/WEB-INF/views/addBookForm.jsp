<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
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
        <li><a href="/bookRegister">Books</a></li>
        <li id="greeting"><a href="/home">Hello, <c:out value="${username}" /></a><li>
        <li id="logout"><a href="logout">Logout</a>
      </ul>  
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<form:form action="/libraries/createBook" method="POST" modelAttibute="book">
  <fieldset class="form-group">
    <label for="book-name">Book name</label>
    <input type="text" name="book-name" class="form-control" id="book-name" placeholder="Book Name">
  </fieldset>
  <fieldset class="form-group">
    <label for="author">Password</label>
    <input type="text" name="author" class="form-control" id="author" placeholder="Author name">
  </fieldset>
  <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</body>
</html>