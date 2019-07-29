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
      <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
      <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <style type="text/css">
         .pass_show{position: relative} 
         .pass_show .ptxt { 
         position: absolute; 
         top: 50%; 
         right: 8%; 
         z-index: 1; 
         color: #f36c01; 
         margin-top: -10px; 
         cursor: pointer; 
         transition: .3s ease all; 
         } 
         .pass_show .ptxt:hover{color: #333333;} 
      </style>
   </head>
   <body>
      <div class="container-fluid">
         
            
            <form id="signupForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/changePassword" style="font-family:Microsoft JhengHei;">
				<h2>變更密碼</h2>
               	<hr>
                  <div class="form-group" >
                  <input type='hidden' id='ContactType' name='ContactType' value='insert'>
                     <label class="control-label col-sm-2"  for="OldPWD">舊密碼:</label>
                     <div class="col-sm-4 pass_show">
                        <input type="password" class="form-control input-lg" class="form-control" id="OldPWD" placeholder="請輸入舊密碼" name="OldPWD">
                     </div>
                     <label class="control-label col-sm-2" for="contactInfo">新密碼</label>
                     <div class="col-sm-4 pass_show">          
                        <input type="password" class="form-control input-lg" class="form-control" id="NewPWD" placeholder="請輸入新密碼" name="NewPWD">
                     </div>
                     <br>
                     <!--<label class="control-label col-sm-2" for="contactInfo">Confirm Password</label>-->
                     <div class="col-sm-4 pass_show">          
                        <input type="password" class="form-control input-lg" class="form-control" id="reNewPWD" placeholder="請重複輸入新密碼" name="reNewPWD">
                     </div>
                     
                  </div>                  

                  <div class="form-group text-left">
                     <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" onclick="return checkpwd()">儲存</button>
                     </div>
                  </div>
               </form>
         
      </div>
      <script>
         $(document).ready(function(){
         $('.pass_show').append('<span class="ptxt">顯示</span>');  
         });
         $(document).on('click','.pass_show .ptxt', function(){ 
         $(this).text($(this).text() == "顯示" ? "隱藏" : "顯示"); 
         $(this).prev().attr('type', function(index, attr){return attr == 'password' ? 'text' : 'password'; }); 
         });
         
         function checkpwd() {
        	 var NewPWD = document.getElementById("NewPWD").value;
        	 var reNewPWD = document.getElementById("reNewPWD").value;
        	 var OldPWD = document.getElementById("OldPWD").value;
        	 if(OldPWD==""){
        		 alert("請輸入舊密碼");
            	 signupForm.OldPWD.focus();
            	 return false; 
        	 }else if(NewPWD=="" && reNewPWD==""){
        		 alert("請輸入新密碼");
            	 signupForm.NewPWD.focus();
            	 return false;
        	 }else if(NewPWD!=reNewPWD){
        		 alert("您輸入的新密碼與確認密碼不一致");
        	     signupForm.reNewPWD.focus();
        	     return false;        	             
        	 }
        	 return true;
        }
      </script>
   </body>
</html>