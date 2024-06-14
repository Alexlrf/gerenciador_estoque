<%@ page  pageEncoding="UTF-8"%>

<div id="msg">
    <p style="color:white; align-self: center;">${ret}</p>
    <button id="btn_fecha_msg" onclick="fecharMsg()"> X </button>
</div>

<style type="text/css">
    #msg {
        z-index: 9;
        width: 95vw;
        background: green;
        color: white;
        height: 20px;
        padding: 10px;
        margin: 10px;
        display: flex;
        justify-content: space-between;
    }

    #btn_fecha_msg {
         height: 20px;
         width: 20px;
         background-color: red;
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
        msg.style.display = 'none'
    }
</script>
