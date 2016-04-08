<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="username" %>
<%@ attribute name="id" %>
<style type="text/css">
	#greeting{
		position: relative;
		left: 40em;
	}
	#logout{
		position: relative;
		left: 40em;
	}
</style>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" href="/libraries/home">Library</a>
    </div>
	
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/libraries/home">Home <span class="sr-only">(current)</span></a></li> 
       	<li><a href="/libraries/books">Books</a></li>
       	<c:forEach var="a" items="${user.getAuthorities()}">
        	<c:choose>
				<c:when test="${a.getAuthority() == 'ROLE_ADMIN' }">
					<li><a href="/libraries/users">Users</a></li>
        		</c:when>
			</c:choose>
		</c:forEach>
		<c:forEach var="a" items="${user.getAuthorities()}">
        	<c:choose>
				<c:when test="${a.getAuthority() == 'ROLE_ADMIN' }">
					<li><a href="/libraries/allLends">Lends</a></li>
        		</c:when>
			</c:choose>
		</c:forEach>
		<li><a href="/libraries/myLends">My Lends</a></li>		
        <li id="greeting"><a href="/libraries/editProfile/${id }">Hello, <c:out value="${username}" /></a><li>
        <li id="logout"><a href="logout">Logout</a>
      </ul>  
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>