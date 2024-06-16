<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${ret.length() > 0}">
        <div id="msg" class="container-fluid d-flex align-middle justify-content-between bg-success" style="visibility: visible">
    </c:when>
    <c:otherwise>
        <div id="msg" class="container d-flex" style="visibility: hidden">
    </c:otherwise>
</c:choose>
    <p class="align-middle text-white" >${ret}</p>
    <button id="btn_fecha_msg" onclick="fecharMsg()" >
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
          <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
          <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
        </svg>
    </button>
</div>

<style type="text/css">
    #msg {
        color: white;
        height: 40px;
        padding: 10px;
        margin: 5px;
        display: flex;
        border-radius: 7px;
    }

    #btn_fecha_msg {
         height: 25px;
         width: 25px;
         background-color: transparent;
         color: white;
         float: right;
         align-self: center;
         border-radius: 50%;
         border: none;
         padding:2px;
         font-weight: bolder;
    }
</style>

<script>
    function fecharMsg() {
        var msg = document.getElementById("msg");
        msg.style.visibility= 'hidden';
        var msg = document.getElementById("btn_fecha_msg");
        msg.style.visibility= 'hidden'
    }
</script>
