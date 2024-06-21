<%@ page pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <body class="semMargin_semPadding">
    <jsp:include page="/shareds_jsp/header.jsp" />
    <div class="container justify-content-center">
      <c:set var="retorno" value="${ret}" scope="request" />
      <c:set var="cor_msg_retorno" value="${cor_msg_retorno}" scope="request" />
      <jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />

      <form action="redirect" class="container d-flex col-9 flex-column">
        <input type="hidden" id="acao" name="acao" value="" />
        <input type="hidden" id="redirect" name="redirect" value="" />

        <!-- SEÇÃO CLIENTES -->
        <div class="container d-flex card col-10 mb-4 shadow">
          <h5 class="card-header">Clientes</h5>
          <div class="card-body container d-flex justify-content-around">
            <div class="card col-5 shadow mb-1 bg-light rounded">
              <h5 class="card-header bg-secondary text-white">
                Listar Clientes &nbsp;&nbsp;
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 512 512"
                  width="19"
                  height="19"
                  fill="currentColor"
                >
                  <path
                    d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"
                  />
                </svg>
              </h5>
              <div class="card-body">
                <h6 class="card-title">Listar clientes cadastrados</h6>
                <input
                  class="btn btn-secondary"
                  type="submit"
                  onclick="redirection('lista')"
                  value="Visualizar"
                />
              </div>
            </div>
            <div class="card col-5 shadow mb-1 bg-light rounded">
              <h5 class="card-header bg-secondary text-white">
                Cadastrar Cliente &nbsp;&nbsp; <i class="fas fa-user-plus"></i>
              </h5>
              <div class="card-body">
                <h6 class="card-title">Cadastrar novos clientes</h6>
                <input
                  class="btn btn-secondary"
                  type="submit"
                  onclick="redirection('cadastro')"
                  value="Cadastrar"
                />
              </div>
            </div>
          </div>
        </div>
        <!-- FIM SEÇÃO CLIENTES -->

        <!-- SEÇÃO PRODUTOS -->
        <div class="container d-flex card col-10 shadow">
          <h5 class="card-header">Produtos</h5>
          <div class="card-body container d-flex justify-content-around">
            <div class="card col-5 shadow mb-1 bg-light rounded">
              <h5 class="card-header bg-secondary text-white">
                Listar Produtos &nbsp;&nbsp;
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 512 512"
                  width="19"
                  height="19"
                  fill="currentColor"
                >
                  <path
                    d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"
                  />
                </svg>
              </h5>
              <div class="card-body">
                <h6 class="card-title">Listar produtos cadastrados</h6>
                <input
                  class="btn btn-secondary"
                  type="submit"
                  onclick="redirection('lista')"
                  value="Visualizar"
                />
              </div>
            </div>

            <div class="card col-5 shadow mb-1 bg-light rounded">
              <h5 class="card-header bg-secondary text-white">
                Cadastrar Produto &nbsp;&nbsp; <i class="fas fa-cart-plus"></i>
              </h5>
              <div class="card-body">
                <h6 class="card-title">Cadastrar novos produtos</h6>
                <input
                  class="btn btn-secondary"
                  type="submit"
                  onclick="redirection('cadastro')"
                  value="Cadastrar"
                />
              </div>
            </div>
          </div>
        </div>
        <!-- FIM SEÇÃO PRODUTOS -->
      </form>
    </div>
    <script>
      function redirection(par) {
        var elemento = document.getElementById("redirect");
        elemento.value = par;
      }
    </script>
  </body>
</html>
