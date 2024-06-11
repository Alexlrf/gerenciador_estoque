<%@ page  pageEncoding="UTF-8"%>
<div id="msg" width="100%" style="background: green; color: white; height: 20px; padding: 10px; margin: 10px;  display: flex; justify-content: space-between;">
    <p style="color:white; align-self: center;">${ret}</p>
    <button onclick="fecharMsg()" style="height:20px; width:20px; background-color: red; color:white; float:right; align-self: center; border-radius: 50%; border: none; padding:2px; font-weight: bolder;"> X </button>
</div>

<script>

    function fecharMsg() {
        var msg = document.getElementById("msg");
        msg.style.display = 'none'

    }

</script>
