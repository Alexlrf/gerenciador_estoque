<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/shareds_jsp/header.jsp" />

<c:set var="retorno" value="${ret}" scope="request" />
<jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />

<form action="pessoa" method="post" id="form">

    <input type="hidden" id="acao"      name="acao"      value="Cadastrar">
    <input type="hidden" id="idContato" name="idContato" value="${param.idContato}">
    <input type="hidden" id="redirect"  name="redirect"  value="">

    <div class="container d-flex flex-column">

        <h2 class="mb-5" >${param.tipoContato.length() > 0 ? 'Edição' : 'Cadastro'} de Pessoa</h2>
        <input class="form-control col-10 mb-4" type="text" name="nome" placeholder="Digite o nome" value="${param.nomeContato}" />

        <div class="d-flex justify-content-between mb-5">
            <input class="form-control me-4" type="email" name="email" placeholder="Digite o e-mail" value="${param.emailContato}" />

            <select class="form-select" name="tipo" value="${param.tipoContato}">
                <option value="${param.tipoContato.length() > 0 ? param.tipoContato : ''}">
                    ${param.tipoContato.length() > 0 ? param.tipoContato : 'Tipo'}
                </option>
                <option value="Cliente">Cliente</option>
                <option value="Fornecedor">Fornecedor</option>
            </select>
        </div>
        <div class="container d-flex justify-content-end">
            <input class="col-2 me-2" type="submit" value="Lista de Usuários" onclick="redirection('lista')"/>
            <input class="col-2" type="submit" value="${param.tipoContato.length() > 0 ? 'Alterar' : 'Salvar'}" onclick="atribuirAcao()">
        </div>
    </div>
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