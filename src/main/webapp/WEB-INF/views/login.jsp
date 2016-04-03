<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<jsp:directive.page contentType="text/html;charset=UTF-8" />
	<head>
	 	<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<title>Login</title>
		
	</head>
	<body>
		  <div class="wrapper">
    <form class="form-signin" name="login" action="/libraries/login" method="POST">       
      <h2 class="form-signin-heading">Please login</h2>
      <input type="text" id="username" class="form-control" name="username" placeholder="Username" required autofocus />
      <input type="password" id="password" class="form-control" name="password" placeholder="Password" required/>      
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
    </form>
  </div>

	</body>
</html>