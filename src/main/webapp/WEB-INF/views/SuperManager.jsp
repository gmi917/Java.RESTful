<%@ page contentType="text/html; charset=UTF-8"%>
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
      <style type="text/css">
         



      </style>
   </head>
   <body>
      <div class="container-fluid">
         <div class="row content">
            <div class="col-sm-2 sidenav">
               <h4>旅遊觀光後台</h4>
               
               <ul class="nav nav-pills nav-stacked">
                  
                  	 <li class="li active"><a id="changeContent" href="#" onclick="changeContent('allAdminMember')">帳號資料管理</a></li>
                  	 <li class="li"><a  href="#" onclick="changeContent('allCategory')">獎品分類資料管理</a></li>                  	                    
                     <li class="li"><a href="logout">登出</a></li>
                     <!-- <li><a href="#section3">Family</a></li>
                     <li><a href="#section3">Photos</a></li>-->
               </ul>
               <br>
            </div>
            <div class="col-sm-10" style="height:90vh">
              <iframe id="iframeContent" src="" frameborder="0"  height="100%" width="100%"></iframe>
            </div>
         </div>
      </div>
      <script>
      document.getElementById("changeContent").click();
		function changeContent(target){
			document.getElementById("iframeContent").src = target;
		}
		 var lis = document.getElementsByClassName("li");
	      for (var i = 0; i < lis.length; i++) {
	    	  lis[i].addEventListener("click", function() {
	        var current = document.getElementsByClassName("active");
	        current[0].className = current[0].className.replace(" active", "");
	        this.className += " active";
	        });
	      }
      </script>
   </body>
</html>