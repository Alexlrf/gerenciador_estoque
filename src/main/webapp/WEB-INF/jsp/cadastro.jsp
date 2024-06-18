<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/shareds_jsp/header.jsp" />

<c:set var="retorno" value="${ret}" scope="request" />
<jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />

<form action="controller" method="post" id="form">

    <input type="hidden" id="acao"      name="acao"      value="Cadastrar">
    <input type="hidden" id="idContato" name="idContato" value="${param.idContato}">
    <input type="hidden" id="redirect"  name="redirect"  value="">

    <div width="90%" style="background: light-gray" class="container_pagina">
        <input type="text" name="nome" placeholder="Digite o nome" value="${param.nomeContato}" />
        <input type="email" name="email" placeholder="Digite o e-mail" value="${param.emailContato}" />

        <select name="tipo" value="${param.tipoContato}">
            <option value="${param.tipoContato.length() > 0 ? param.tipoContato : ''}">
                ${param.tipoContato.length() > 0 ? param.tipoContato : 'Tipo'}
            </option>
            <option value="Cliente">Cliente</option>
            <option value="Fornecedor">Fornecedor</option>
        </select>

        <input type="submit" value="Incluir" onclick="atribuirAcao()">
    </div>
    <input type="submit" value="Lista de Usuários" onclick="redirection('lista')"/>
</form>

<script>
    function atribuirAcao() {
        var idCont = document.getElementById("idContato");
        if(idCont.value > 0) {
           var acao = document.getElementById("acao");
           acao.value = 'Alterar'
        }
    }

    function redirection(par) {
        var form = document.getElementById("form");
        form.action = 'redirect';
        form.method = 'post';
        var elemento = document.getElementById("redirect");
        elemento.value = par;
        var elemento = document.getElementById("acao");
        elemento.value = '';
    }

</script>