<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Controle de Estoque</title>
    </head>
    <body class="semMargin_semPadding">
         <jsp:include page="/shareds_jsp/header.jsp" />
        <div class="container_pagina">
            <c:if test="${ret.length() > 0}">
                <jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />
            </c:if>
            <form action="redirect">
                <input type="hidden" id="acao"      name="acao"      value="">
                <input type="hidden" id="redirect"  name="redirect"  value="">
                <div style="height: 20%; width: 20%; background-color: LightGray; margin:20px;">
                    <h4>Listar</h4>
                    <input type="submit" onclick="redirection('lista')" value="Listar" >
                </div>
                <div style="height: 20%; width: 20%; background-color: LightGray; margin:20px;">
                    <h4>Cadastrar</h4>
                    <input type="submit" onclick="redirection('cadastro')" value="Cadastrar" >
                </div>
            </form>
        </div>
        <script>
            function redirection(par) {
                var elemento = document.getElementById("redirect");
                elemento.value = par
            }
        </script>
    </body>
</html>
