<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/shareds_jsp/header.jsp" />

<div class="container_pagina">
    <c:if test="${ret.length() > 0}">
        <jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />
    </c:if>

    <form action="redirect">
        <input type="hidden" name="redirect" value="cadastro">
        <input type="submit" value="Incluir novo usuário" />
    </form>

    <form action="controller" id="form">
        <input type="hidden" id="acao"          name="acao"         value="Listar">
        <input type="hidden" id="redirect"      name="redirect"     value="">
        <input type="hidden" id="idContato"     name="idContato"    value="">
        <input type="hidden" id="nomeContato"   name="nomeContato"  value="">
        <input type="hidden" id="emailContato"  name="emailContato" value="">

        <input type="submit" value="Listar Contatos de Usuários">

        <table border="1" width="100%" style="margin-top:20px">
            <thead>
                <th>Código</th>
                <th>Nome</th>
                <th>E-mail</th>
                <th>Ações</th>
            </thead>
            <tbody>
                <c:forEach var="contato" items="${contatos}">
                    <tr>
                        <td>${contato.id}</td>
                        <td>${contato.nome}</td>
                        <td>${contato.email}</td>
                        <td>
                            <div style="display:flex; justify-content: space-evenly;">
                                <a href="controller?id=${contato.id}&acao=Remover">
                                    <img src="imagens/excluir.png" alt="  Imagem de icone de lixeira para excluir registro">
                                </a>
                                <button type="submit" style="border: none; background-color: transparent;" onclick="atribuirRedirect('cadastro', '${contato.id}', '${contato.nome}', '${contato.email}')">
                                    <img src="imagens/editar.png" alt="  Imagem de icone de Lápis para editar registro" />
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
        function atribuirRedirect(par, idCont, nmCont, emailCont) {
            var form = document.getElementById("form");
            form.action = 'redirect'
            form.method = 'post'
            var elemento = document.getElementById("redirect");
            elemento.value = par
            var contatoEditar = document.getElementById("idContato");
            contatoEditar.value = idCont
            var nmContatoEditar = document.getElementById("nomeContato");
            nmContatoEditar.value = nmCont
            var emailContatoEditar = document.getElementById("emailContato");
            emailContatoEditar.value = emailCont
        }

</script>

