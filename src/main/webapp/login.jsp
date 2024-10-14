<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
    />
    <title>Controle de Estoque</title>
  </head>
  <body class="container col-3 mt-5">
        <main class="form-signin">
          <form action="loginServlet" method="post" id="form">

            <h1 class="h3 mb-3 fw-normal">ENTRAR</h1>

            <div class="form-floating">
              <input type="text" class="form-control" id="login" name="login" placeholder="usuario" >
              <label for="login">Usuário</label>
            </div>
            <div class="form-floating">
              <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha">
              <label for="senha">Senha</label>
            </div>

            <div class="checkbox mb-3">
              <label>
                <input type="checkbox" value="remember-me"> Remember me
              </label>
            </div>
            <button class="w-100 btn btn-lg btn-secondary" type="submit">Entrar</button>
            <p class="mt-5 mb-3 text-muted">© 2017–2021</p>
          </form>
        </main>
  </body>
</html>