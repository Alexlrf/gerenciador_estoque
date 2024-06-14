<%@ page  pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

<body class="container-fluid m-0 p-0">
    <header>
       <form action="logout">
          <div id="container_header" width: 100%;>
             <a href="/controleEstoque" class="text-decoration-none text-white" title="Ir para home"><h2>Controle de Estoque</h2></a>
             <button id="btn_logout" class="btn btn-secondary btn-sm" type="submit">
                 <svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                   <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z"/>
                   <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"/>
                 </svg>
                 &nbsp;&nbsp;
                 Sair
             </button>
          </div>
       </form>
    </header>
</body>

<style type="text/css">
    #container_header {
         background: black;
         color: white;
         padding: 10px;
         margin-bottom: 10px;
         display: flex;
         justify-content: space-between;
    }

    #btn_logout {
         color: white;
         float: right;
         align-self: center;
    }
</style>