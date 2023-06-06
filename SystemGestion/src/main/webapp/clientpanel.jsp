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

	<%! client userActif; %>
	<%! String op; %>
	<c:choose>
		<c:when test="${user == null}"><% request.getRequestDispatcher("login.jsp").forward(request, response); %></c:when>
		<c:when test="${user.role == TypeUser.admin}"><% userActif = (client) request.getAttribute("userActif"); op="admin";%></c:when>
		<c:when test="${user.role == TypeUser.client}"><% userActif = (client) session.getAttribute("user");op="client"; %></c:when>
	</c:choose>

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
    <title>Client Panel</title>
</head>
<body>
	
    <div class="container-fluid d-flex justify-content-between align-items-center px-4" style="height: 100px;">
        <div class="d-flex align-items-center">
            <a href="clientPanelCONTROL?login=<%=userActif.getLogin()%>" style="text-decoration: none; color: black;" ><img src="style/logo.png" alt="logo" width="65px" style="margin-bottom: 10px; ">&nbsp;&nbsp;
            <span style="font-family: 'Jost', sans-serif; text-transform: uppercase;font-size: 25px; font-weight: 600;text-decoration: none; color: black;"><%=userActif.getLogin()%></span></a>
        </div>
        <div class="d-flex">
            <form class="d-flex" method="get" action="clientPanelCONTROL">
                <input class="form-control me-2" type="search" placeholder="Ex : R100123314" name="search" aria-label="search" required>
                <c:if test="${user.role == TypeUser.admin}"><input class="form-control me-2" type="search" placeholder="Ex : R100123314" name="login" value="${userActif.getLogin()}" aria-label="search" hidden=""></c:if>
                <input class="btn btn-outline-primary" type="submit" value="Chercher"/>
              </form>
            <a href="clientPanelCONTROL?logOut=<%=userActif.getLogin()%>"><img src="style/log-out.png" alt="logout" width="40px" style="cursor: pointer;"></a>
        </div>
    </div>

    <!--table-->
    <div class="container my-5 d-flex flex-column">
        <table class="table table-bordered">
          <thead class="bg-primary text-white">
            <tr>
              <th>#</th>
              <th>ID numérique</th>
              <th>Nom</th>
              <th>Prénom</th>
              <th>CNE</th>
              <c:choose>
	              <c:when test="${user.role == TypeUser.client}"><th>Modifier</th></c:when>
	              <c:when test="${user.role == TypeUser.admin}"><th>Supprimer</th></c:when>
              </c:choose>
            </tr>
          </thead>
          <tbody>
          
          <c:forEach items="${liste}" var="u" varStatus="count">
            <tr class="table-light">
              <td>${count.index+1}</td>
              <td>${u.getId()}</td>
              <td>${u.getNom()}</td>
              <td>${u.getPrenom()}</td>
              <td class="cni">${u.getCne()}</td>
              <c:choose>
					<c:when test="${user.role == TypeUser.client}"><td><a href="clientPanelCONTROL?update=${u.getCne()}"><img src="style/edit.png" alt="edit" width="30px"></a></td></c:when>
					<c:when test="${user.role == TypeUser.admin}">
						<td><a href="clientPanelCONTROL?login=${userActif.getLogin()}&delete=${u.getCne()}"><img src="style/d22.svg" alt="edit" width="30px"></a></td>
					</c:when>
			  </c:choose>
            </tr>
          </c:forEach>
           
        </tbody>
    </table>
    <c:if test="${user.role == TypeUser.admin}"><a href="adminPanelCONTROL?login=${userActif.getLogin()}&addCasier=true" class="btn btn-primary" style="width: 30%; margin: auto;"><img src="style/addcasiers.svg" alt="edit" width="25px" style="padding-bottom: 3px">&nbsp; Ajouter Casier</a></c:if>
  </div>
  <c:if test="${user.role == TypeUser.admin}"><a href="adminPanelCONTROL?" ><img src="style/turn.svg" alt="edit" width="50px" style="margin-left: 20px;"></a></c:if>

</body>
</html>