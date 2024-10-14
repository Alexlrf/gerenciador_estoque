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
  <body class="semMargin_semPadding">
    <jsp:include page="/shareds_jsp/header.jsp" />
    <div class="container justify-content-center">
      <c:set var="retorno" value="${ret}" scope="request" />
      <c:set var="cor_msg_retorno" value="${cor_msg_retorno}" scope="request" />
      <jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />

      <jsp:include page="/WEB-INF/pessoa/pessoaIndex.jsp" />

      <jsp:include page="/WEB-INF/produto/produtoIndex.jsp" />

    </div>
  </body>
</html>
