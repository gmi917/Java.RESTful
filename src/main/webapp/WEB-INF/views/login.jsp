<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
   /*避免瀏覽器因cache而無法看到最新資料*/
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>賽事APP-旅遊觀光後台</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
      <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <!--===============================================================================================-->
      <!--<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">-->
      <!--===============================================================================================-->
      <!--<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">-->
      <!--===============================================================================================-->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/icon-font.min.css">
      <!--===============================================================================================-->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/animate.css">
      <!--===============================================================================================-->	
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/hamburgers.min.css">
      <!--===============================================================================================-->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/animsition.min.css">
      <!--===============================================================================================-->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/select2.min.css">
      <!--===============================================================================================-->	
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/daterangepicker.css">
      <!--===============================================================================================-->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/util.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/main.css">
      <!--===============================================================================================-->
      <script src="${pageContext.request.contextPath}/js/animsition.min.js"></script>
      <!--===============================================================================================-->
      <script src="${pageContext.request.contextPath}/js/popper.js"></script>
      <!--===============================================================================================-->
      <script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
      <!--===============================================================================================-->
      <script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
      <script src="${pageContext.request.contextPath}/js/daterangepicker.js"></script>
      <!--===============================================================================================-->
      <script src="${pageContext.request.contextPath}/js/countdowntime.js"></script>
      <!--===============================================================================================-->
      <script src="${pageContext.request.contextPath}/js/main.js"></script>
   </head>
   <body>
      <div class="limiter">
         <div class="container-login100">
            <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
               <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/login" method="post">
                  <span class="login100-form-title p-b-32">
                  Account Login
                  </span>
                  <span class="txt1 p-b-11">
                  帳號
                  </span>
                  <div class="wrap-input100 validate-input m-b-36" data-validate = "Username is required">
                     <input class="input100" type="text" name="MemberID" >
                     <span class="focus-input100"></span>
                  </div>
                  <span class="txt1 p-b-11">
                  密碼
                  </span>
                  <div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
                     <input class="input100" type="password" name="MemberPWD" >
                     <span class="focus-input100"></span>
                  </div>
                  <span class="txt1 p-b-11">
                  圖型驗證碼
                  </span>
                  <div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
                     <input type="tel" class="form-control" name="usercode" placeholder="請輸入圖型驗證碼" />
                  </div>
                  <span>
                     <img id="codeimg" alt="驗證碼" src="${pageContext.request.contextPath}/Image" width="120" height="45">
                     <a href="javascript:reloadCode();">換一張</a>
                  </span>
                  <br>
                  <div class="container-login100-form-btn">
                     <button class="login100-form-btn">
                     Login
                     </button>
                  </div>
               </form>
            </div>
         </div>
      </div>
      <div id="dropDownSelect1"></div>
   </body>
   <script>
     
      function reloadCode(){
    	 var time = new Date().getTime();//創建不同的時間
    	 $("#codeimg").attr("src","${pageContext.request.contextPath}/Image?time="+time);//因為時間參數不同，請求重新
      }
   </script>
</html>