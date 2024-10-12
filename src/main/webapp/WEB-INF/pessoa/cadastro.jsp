<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/redirect.js"></script>

<jsp:include page="/shareds_jsp/header.jsp" />

<c:set var="retorno" value="${ret}" scope="request" />
<jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />

<form action="pessoa" method="post" id="form" enctype="multipart/form-data">

    <input type="hidden" id="acao"      name="acao"      value="Cadastrar">
    <input type="hidden" id="idContato" name="idContato" value="${idContato}">
    <input type="hidden" id="redirect"  name="redirect"  value="lista">
    <input type="hidden" id="tipoBusca" name="tipoBusca" value="">

    <div class="container d-flex flex-column">

        <div class="container d-flex justify-content-between">
            <h2 class="mb-5" >${tipoContato.length() > 0 ? 'Edição' : 'Cadastro'} de Pessoa</h2>
           <c:if test="${tipoContato.length() > 0}">
               <img style="width:150px; margin-top:-40" src="imagem?id=${idContato}" alt="Imagem de ${nomeContato}">
           </c:if>
        </div>

        <input class="form-control col-10 mb-4" type="text" name="nome" placeholder="Digite o nome" value="${nomeContato}" />

        <div class="d-flex justify-content-between mb-5">
            <input class="form-control me-4" type="email" name="email" placeholder="Digite o e-mail" value="${emailContato}" />

            <select class="form-select" name="tipo" value="${tipoContato}">
                <option value="${tipoContato.length() > 0 ? tipoContato : ''}">
                    ${tipoContato.length() > 0 ? tipoContato : 'Tipo'}
                </option>
                <option value="Cliente">Cliente</option>
                <option value="Fornecedor">Fornecedor</option>
            </select>
        </div>
        <div class="container d-flex justify-content-end">
            <input class="col-2 me-2" type="submit" value="Lista de Usuários" onclick="irParaListaPessoa()"/>
            <input class="col-2" type="submit" value="${tipoContato.length() > 0 ? 'Alterar' : 'Salvar'}" onclick="atribuirAcao()">
        </div>
        <input type="file" id="imgContato" name="imgContato"/>
    </div>
</form>

<script>

    function atribuirAcao() {
        var idCont = document.getElementById("idContato");
        if(idCont.value > 0) {
           document.getElementById("acao").value = 'Alterar'
        }
    }

    function irParaListaPessoa() {
       document.getElementById("acao").value      = 'Listar';
       document.getElementById("redirect").value  = 'lista';
       document.getElementById("tipoBusca").value = 'TODOS';
    }

</script>