<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js">
</script>
</head>

<script>
$(document).ready(function(){
  $("button").click(function(){
    $.post("${pageContext.request.contextPath}/userlogin",
    {
    	UserAccount:"test",
    	UserPWD:"@",      
    },
    function(data,status){
      alert("資料" + data + "\n狀態" + status);
    });
  });
});
</script>

<body>

<button>send json</button>

</body>
</html>