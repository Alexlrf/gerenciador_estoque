<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

<jsp:include page="/shareds_jsp/header.jsp" />


<c:set var="retorno" value="${ret}" scope="request" />
<c:set var="cor_msg_retorno" value="${cor_msg_retorno}" scope="request" />
<jsp:include page="/shareds_jsp/mensagemRetorno.jsp" />

<form action="pessoa" id="form">
    <input type="hidden" id="acao"          name="acao"         value="Listar">
    <input type="hidden" id="redirect"      name="redirect"     value="">
    <input type="hidden" id="idContato"     name="idContato"    value="">
    <input type="hidden" id="nomeContato"   name="nomeContato"  value="">
    <input type="hidden" id="emailContato"  name="emailContato" value="">
    <input type="hidden" id="tipoContato"  name="tipoContato"   value="">
    
    <div class="container d-flex flex-column">
        <h2 class="mb-5" >Pesquisa de Pessoa</h2>
        
        <div class="container d-flex justify-content-between mb-4">
            <div class="col-2 d-flex">
                <input name="fragmentoTexto" id="fragmentoTexto" type="text" class="form-control col-2 me-1" onkeyup="validarQuantidadeCaracteres()">
                <button id="btnBuscaFragmeno" type="submit" class="btn btn-outline-secondary" disabled onclick="buscarPessoaPorFragmentoTexto('pessoa')">Buscar</button>
            </div>
            <div class="col-2">
                <select id="select_tipo_pessoa" class="form-select" name="tipo" onchange="return buscarPessoasPorCategoria('pessoa', this.value)">
                    <option value="">Busca por tipo</option>
                    <option value="Todos">Todos</option>
                    <option value="Cliente">Cliente</option>
                    <option value="Fornecedor">Fornecedor</option>
                </select>
            </div>
            <button type="submit"
                    class="btn btn-outline-secondary"
                    onclick="atribuirRedirect('cadastro', null, null, null)">
                 <i class="fas fa-user-plus"></i>
                 Incluir novo usuário
            </button>
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
                                <a href="pessoa?id=${contato.id}&acao=Remover">
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

    <form action="relatorio" target="_blank">
        <input type="submit" value="Gerar Relatório" />
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

        function buscarTodasPessoas(formParam) {
            location.href=formParam+"?acao=Listar&tipoBusca=TODOS";
        }

        function buscarPessoasPorCategoria(formParam, valorParam) {
            location.href=formParam+"?acao=Listar&tipoBusca=TIPO&valorBusca="+valorParam;
        }
        
        function buscarPessoaPorFragmentoTexto(formParam) {
            let texto = document.getElementById('fragmentoTexto').value.trim();
            location.href=formParam+"?acao=Listar&tipoBusca=FRAGMENTO_TEXTO&valorBusca="+texto;
        }
        
        function validarQuantidadeCaracteres() {
            let texto = document.getElementById('fragmentoTexto').value.trim();
            if(texto.length > 2) {
                document.getElementById("btnBuscaFragmeno").disabled = false;
            }
        }

        function stopDefAction(evt) {
            evt.preventDefault();
        }

        document
        .getElementById("btnBuscaFragmeno")
        .addEventListener("click", stopDefAction, false);

</script>

