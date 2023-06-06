<%@page import="com.mysql.cj.Session"%>
<%@page import="classMetier.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%! String opAdmin = ""; %>
		<%!String op; %>
		<%!String test; %>
		<c:choose>
				<c:when test="${addCasier != null}"><% opAdmin = "admin" ; opAdmin = ""; op = "Ajouter"; %></c:when>
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
    <title><%=op %> Casiers</title>
</head>
<body class="bg-light">
    <main class="form-signin text-center">
        <form method="post" action="adminPanelCONTROL">
          <img class="mb-4" src="style/operation.png" alt="logo" width="120" height="120">
     		<c:if test="${addCasier != null}">
     		<div class="form-floating my-2">
            <input type="text" class="form-control" id="floatingInput" name="login" value="${login}" readonly="readonly">
            <label for="floatingInput">Client</label>
          </div>
          </c:if>
          <div class="form-floating my-2">
            <input type="text" class="form-control" id="floatingInput" name="id"  <%=opAdmin %> <c:if test="${addCasier == null}">value="${cas.getId()}"</c:if>placeholder="Id" required>
            <label for="floatingInput">Id Numerique</label>
          </div>
          <div class="form-floating my-2">
            <input type="text" class="form-control" id="floatingInput" name="nom" <c:if test="${addCasier == null}">value="${cas.getNom()}"</c:if>placeholder="Nom" required >
            <label for="floatingInput">Nom</label>
          </div>
          <div class="form-floating my-2">
            <input type="text" class="form-control" id="floatingInput" name="prenom" <c:if test="${addCasier == null}">value="${cas.getPrenom()}"</c:if> placeholder="Prénom" required >
            <label for="floatingInput">Prénom</label>
          </div>
          <div class="form-floating my-2">
            <input type="text" class="form-control" id="floatingInput" name="cne" placeholder="CNE" required <c:if test="${addCasier == null}">value="${cas.getCne()}"</c:if> />
            <label for="floatingInput">CNE</label>
          </div>
          <input class="w-100 btn btn-lg btn-primary" type="submit" name="opCasier" value="<%=op %>"/>
        </form>
		</main>
		<a href="clientPanelCONTROL<c:if test="${addCasier != null}">?login=${login}</c:if>"><img src="style/turn.svg" alt="edit" width="50px" style="margin-left: 20px;"></a>
</body>
</html>