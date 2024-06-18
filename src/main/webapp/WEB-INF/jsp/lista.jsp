<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">


<jsp:include page="/shareds_jsp/header.jsp" />

<div class="container_pagina">

    <c:set var="retorno" value="${ret}" scope="request" />
    <c:set var="cor_msg_retorno" value="${cor_msg_retorno}" scope="request" />
    <jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />

    <form action="controller" id="form">
        <input type="hidden" id="acao"          name="acao"         value="Listar">
        <input type="hidden" id="redirect"      name="redirect"     value="">
        <input type="hidden" id="idContato"     name="idContato"    value="">
        <input type="hidden" id="nomeContato"   name="nomeContato"  value="">
        <input type="hidden" id="emailContato"  name="emailContato" value="">
        <input type="hidden" id="tipoContato"  name="tipoContato"   value="">

        <div class="container-fluid d-flex justify-content-between mb-4">
            <input type="submit" value="Listar Contatos de Usuários">
            <button type="submit"
                    class="btn btn-outline-secondary"
                    onclick="atribuirRedirect('cadastro', null, null, null)">
                 <i class="fas fa-user-plus"></i>  Incluir novo usuário</button>
        </div>

        <table class="table table-striped">
            <thead class="col-12">
                <th class="col-1">Código</th>
                <th class="col-5">Nome</th>
                <th class="col-4">E-mail</th>
                <th class="col-4">Tipo</th>
                <th class="col-2 text-center">Ações</th>
            </thead>
            <tbody>
                <c:forEach var="contato" items="${contatos}">
                    <tr>
                        <td>${contato.id}</td>
                        <td>${contato.nome}</td>
                        <td>${contato.email}</td>
                        <td>${contato.tipo}</td>
                        <td>
                            <div style="display:flex; justify-content: space-evenly;">
                                <a href="controller?id=${contato.id}&acao=Remover">
                                    <img src="imagens/excluir.png" alt="Imagem de icone de lixeira para excluir registro" title="Excluir registro">
                                </a>
                                <button type="submit" style="border: none; background-color: transparent;" onclick="atribuirRedirect('cadastro', '${contato.id}', '${contato.nome}', '${contato.email}', '${contato.tipo}')">
                                    <img src="imagens/editar.png" alt="Imagem de icone de Lápis para editar registro"  title="Editar registro"/>
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<script>
        function atribuirRedirect(par, idCont, nmCont, emailCont, tipoCont) {
            var form = document.getElementById("form");
            form.action = 'redirect'
            form.method = 'post'
            var elemento = document.getElementById("redirect");
            elemento.value = par

            if(idCont) {
                var contatoEditar = document.getElementById("idContato");
                contatoEditar.value = idCont
                var nmContatoEditar = document.getElementById("nomeContato");
                nmContatoEditar.value = nmCont
                var emailContatoEditar = document.getElementById("emailContato");
                emailContatoEditar.value = emailCont
                var tipoContatoEditar = document.getElementById("tipoContato");
                tipoContatoEditar.value = tipoCont
            }
        }

</script>

