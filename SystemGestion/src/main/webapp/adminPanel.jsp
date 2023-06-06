<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="com.mysql.cj.Session"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mysql.cj.xdevapi.Result"%>
<%@page import="org.eclipse.jdt.internal.compiler.IDebugRequestor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="classMetier.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<c:if test="${user == null or user.role != 'admin'}">
		<% request.getRequestDispatcher("login.jsp").forward(request, response); %>
	</c:if>
	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="style/logo.png" type="image/x-icon">
    <style>
  		@import url('https://fonts.googleapis.com/css2?family=Jost:ital@1&display=swap');
	</style>
    <link rel="stylesheet" href="style/panel.css">
    <title>Admin Panel</title>
</head>
<body>

    <div class="container-fluid d-flex justify-content-between align-items-center px-4" style="height: 100px;">
        <div class="d-flex align-items-center">
            <a href="adminPanelCONTROL" style="text-decoration: none; color: black;"><img src="style/logo.png" alt="logo" width="65px" style="margin-bottom: 10px; ">&nbsp;&nbsp;
            <span style="font-family: 'Jost', sans-serif; text-transform: uppercase;font-size: 25px; font-weight: 600;text-decoration: none; color: black;">${user.getLogin()}</span></a>
        </div>
        <div class="d-flex">
            <form class="d-flex" method="get" action="adminPanelCONTROL">
                <input class="form-control me-2" type="search" placeholder="By Identifiant" name="search" aria-label="search" required>
                <button class="btn btn-outline-primary" type="submit">Chercher</button>
              </form>
            <a href="adminPanelCONTROL?logOut=${user.getLogin()}"><img src="style/log-out.png" alt="logout" width="40px" style="cursor: pointer;"></a>
        </div>
    </div>

    <!--table-->
    <div class="container my-5 d-flex flex-column">
        <table class="table table-bordered">
          <thead class="bg-primary text-white">
            <tr>
              <th>#</th>
              <th>Login</th>
              <th>Password</th>
              <th>role</th>
              <th>Modifier</th>
              <th>Liste des casiers</th>
            </tr>
          </thead>
          <tbody>
          
          <c:forEach items="${liste}" var="u" varStatus="count">
            <tr class="table-light">
              <td>${count.index+1}</td>
              <td>${u.getLogin()}</td>
              <td>${u.getPassword()}</td>
              <td>${u.getRole()}</td>
              <td><a href="adminPanelCONTROL?update=${u.getLogin()}"><img src="style/edit.png" alt="edit" width="30px"></a></td>
              <td><a href="clientPanelCONTROL?login=${u.getLogin()}&liste=true"><img src="style/listb.svg" alt="edit" width="30px"></a></td>
            </tr>
          </c:forEach>
           
        </tbody>
    </table>
    <a href="adminPanelCONTROL?addUser=new" class="btn btn-primary" style="width: 30%; margin: auto"><img src="style/add1.svg" alt="edit" width="25px" style="padding-bottom: 3px;">&nbsp; Ajouter Utilisateur</a>
  </div>
      

</body>
</html>