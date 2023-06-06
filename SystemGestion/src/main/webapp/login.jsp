<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="style/logo.png" type="image/x-icon">
	<link href="style/index.css" rel="stylesheet">
    <title>S'authentifier</title>
</head>
<body class="bg-light text-center">
    
    <main class="form-signin">
        <form method="post" action="loginCONTROL">
          <img class="mb-4" src="style/logo.png" alt="logo" width="120" height="120">
          <h1 class="h3 mb-3 fw-normal">S'authentifier</h1>
      
          <div class="form-floating my-2">
            <input type="text" class="form-control" id="floatingInput" name="login" placeholder="identifiant" required>
            <label for="floatingInput">Identifiant</label>
          </div>
          <div class="form-floating my-2">
            <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password" required>
            <label for="floatingPassword">Mot de passe</label>
          </div>
      
          <div class="checkbox mb-3">
            <label>
              <input type="checkbox" value="remember-me"> Se souvenir de moi
            </label>
          </div>
          <button class="w-100 btn btn-lg btn-primary" type="submit">Connexion</button>
          <p class="mt-2 mb-3 text-muted">&copy; 2022-2023</p>
        </form>

        <p style="color: red; font-family: bold">${msg}</p>

      </main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" ></script>
</body>
</html>