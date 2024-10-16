<%@ page  pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

<body>
    <form action="produto" class="container d-flex col-9 flex-column" method="post" id="formProduto" enctype="multipart/form-data">
        <input type="hidden" id="acao" name="acao" value="" />
        <input type="hidden" id="redirect" name="redirect" value="" />
        <input type="hidden" id="tipoBusca" name="tipoBusca" value="" />

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
      </form>
    <script>
        function buscarTodosProdutos() {
          var elementoAcao = document.getElementById("acao");
          elementoAcao.value = 'Listar';

          var elementoRedirect = document.getElementById("redirect");
          elementoRedirect.value = 'Listar';

          var elementoBusca = document.getElementById("tipoBusca");
          elementoBusca.value = 'TODOS';
      }

      function cadastrarProduto() {
          var elementoAcao = document.getElementById("acao");
          elementoAcao.value = 'Cadastrar';

          var elementoRedirect = document.getElementById("redirect");
          elementoRedirect.value = 'Cadastrar';
      }
    </script>
</body>

