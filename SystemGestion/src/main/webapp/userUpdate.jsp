<%@page import="com.mysql.cj.xdevapi.Client"%>
<%@page import="classMetier.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<%!String opAdmin; %>
	<%!String op; %>

	<c:choose>
			<c:when test="${addUser != null}"><% opAdmin = "" ;  op = "Ajouter";  %></c:when>
			<c:otherwise><% opAdmin = "readonly=\"readonly\""; op = "Modifier";%></c:otherwise>
	</c:choose>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="style/logo.png" type="image/x-icon">
	<link href="style/update.css" rel="stylesheet">
    <title><%=op %> Users</title>
</head>
<body class="bg-light ">
    <main class="form-signin text-center">
        <form method="post" action="adminPanelCONTROL">
          <img class="mb-4" src="style/operation.png" alt="logo" width="120" height="120">
     
          <div class="form-floating my-2">
            <input type="text" class="form-control" id="floatingInput" name="login" autofocus="autofocus" <c:if test="${addUser == null}"> value="${userUp.login}" <%=opAdmin %></c:if>  >
            <label for="floatingInput">Login</label>
          </div>
          <div class="form-floating my-2">
            <input type="text" class="form-control" id="floatingInput" name="password"  <c:if test="${addUser == null}">value="${userUp.password }"</c:if>>
            <label for="floatingInput">Password</label>
          </div>
          <c:choose>
          		<c:when test="${addUser != null}">
          			<select class="form-select" aria-label="Disabled select example" name="role" <%=opAdmin %> >
					  <option  value="client" selected>Client</option>
					  <option value="admin" >Admin</option>
				  	</select>
				  	<br>
          		</c:when>
          		<c:otherwise>
          			<div class="form-floating my-2">
			            <input type="text" class="form-control" id="floatingInput" name="role" value="${userUp.role }" <%=opAdmin %>>
			            <label for="floatingInput">Role</label>
			        </div>
          		</c:otherwise>
          </c:choose>
          <input class="w-100 btn btn-lg btn-primary" type="submit" name="opUser" value="<%=op%>"/>
        </form>
      </main>
      <a href="adminPanelCONTROL" ><img src="style/turn.svg" alt="edit" width="50px" style="margin-left: 20px;"></a>
</body>
</html>