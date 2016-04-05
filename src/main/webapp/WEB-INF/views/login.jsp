<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<title>Login</title>
		<style type="text/css">
			@media (min-width: 768px) {
			    .omb_row-sm-offset-3 div:first-child[class*="col-"] {
			        margin-left: 25%;
			    }
			}
			.omb_login{
				margin-top: 10em;
			}
			#btn{
				margin-top: 1em;
			}
			h3{
				text-align: center;
			}
			.omb_login .omb_loginForm .input-group.i {
				width: 2em;
			}
		</style>
	</head>
	<body>
	 <div class="container">
	 	
	    <div class="omb_login">
	    	<h3>Login</h3>
			<div class="row omb_row-sm-offset-3">
				<div class="col-xs-12 col-sm-6">	
				    <form class="omb_loginForm" name="login" action="/libraries/login" method="POST">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user"></i></span>
							<input type="text" class="form-control" name="username" placeholder="Username">
						</div>
						<span class="help-block"></span>
											
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input  type="password" class="form-control" name="password" placeholder="Password">
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button id="btn" class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
					</form>
				</div>
	    	</div>
	</div>
   </div>

	</body>
</html>