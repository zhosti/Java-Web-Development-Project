<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/libraries/lendBook/${id}" method="POST" modelAttibute="book">
	  <fieldset class="form-group">
	    <label for="lend-date">Lend Date</label>
	    <input type="date" name="lend-date" class="form-control" id="lend-date" placeholder="Lend Date">
	  </fieldset>
	  <fieldset class="form-group">
	    <label for="return-date">Return Date</label>
	    <input type="date" name="return-date" class="form-control" id="return-date" placeholder="Return Date">
	  </fieldset>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form:form>
</body>
</html>