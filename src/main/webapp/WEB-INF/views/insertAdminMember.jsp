<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%
   /*避免瀏覽器因cache而無法看到最新資料*/
   response.setHeader("Pragma", "no-cache");
   response.setHeader("Cache-Control", "no-cache");
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
         <div class="row content">
            <div class="col-sm-10">
               <h2>帳號資料管理>新增</h2>
               <hr>
               <form id="addForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/addAdminMember" style="font-family:Microsoft JhengHei;font-size:3vh;">                  
                  <div class="form-group" >
                     <label class="control-label col-sm-3"><font color="#FF0000">*</font>廠商帳號:
                     <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">字數限制20個字</p>
                     </label>
                     <div class="col-sm-4">
                        <input type="text" class="form-control input-lg" id="MemberID" placeholder="請輸入廠商帳號" name="MemberID" onKeyDown="LimitMemberIDInput(this)" onKeyUp="LimitMemberIDInput(this)" onkeypress="LimitMemberIDInput(this)">
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-3"><font color="#FF0000">*</font>廠商密碼:</label>
                     <div class="col-sm-4 pass_show">          
                     <input type="password" class="form-control input-lg" id="MemberPWD" placeholder="請輸入廠商密碼" name="MemberPWD">
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-3">廠商名字:
                     <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">字數限制250個字</p>
                     </label>
                     <div class="col-sm-4">          
                      <input type="text" class="form-control input-lg" id="MemberName" placeholder="請輸入廠商名字" name="MemberName" onKeyDown="LimitMemberNameInput(this)" onKeyUp="LimitMemberNameInput(this)" onkeypress="LimitMemberNameInput(this)">
                     </div>
                  </div>       
                  <div class="form-group">
                     <label class="control-label col-sm-3"><font color="#FF0000">*</font>廠商統編:
                     <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">字數限制8個字</p>
                     </label>
                     <div class="col-sm-4">          
                      <input type="text" class="form-control input-lg" id="MemberNumber" placeholder="請輸入廠商統編" name="MemberNumber" onKeyDown="LimitMemberNumberInput(this)" onKeyUp="LimitMemberNumberInput(this)" onkeypress="LimitMemberNumberInput(this)">
                     </div>
                  </div>       
                  <div class="form-group text-right">
                     <div class="col-sm-offset-2 col-sm-5">
                        <button type="submit" class="btn btn-default" onclick="return checkValue()">儲存</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
       
      <script>
      function LimitMemberIDInput(field){ 
    	  maxlimit=20; 
    	  if (field.value.length > maxlimit) 
    	  field.value = field.value.substring(0, maxlimit); 
    	  }
      function LimitMemberNameInput(field){ 
    	  maxlimit=250; 
    	  if (field.value.length > maxlimit) 
    	  field.value = field.value.substring(0, maxlimit); 
    	  }
      function LimitMemberNumberInput(field){ 
    	  maxlimit=8; 
    	  if (field.value.length > maxlimit) 
    	  field.value = field.value.substring(0, maxlimit); 
    	  }
      $(document).ready(function(){
          $('.pass_show').append('<span class="ptxt">顯示</span>');  
          });
          $(document).on('click','.pass_show .ptxt', function(){ 
          $(this).text($(this).text() == "顯示" ? "隱藏" : "顯示"); 
          $(this).prev().attr('type', function(index, attr){return attr == 'password' ? 'text' : 'password'; }); 
          });
          
          function checkValue() {
         	 var MemberID = document.getElementById("MemberID").value;
         	 var MemberPWD = document.getElementById("MemberPWD").value;  
         	 var MemberNumber = document.getElementById("MemberNumber").value;
         	 if(MemberID==""){
         		 alert("請輸入廠商帳號");
         		addForm.MemberID.focus();
             	 return false; 
         	 }else if(MemberPWD==""){
         		 alert("請輸入廠商密碼");
         		addForm.MemberPWD.focus();
             	 return false;
         	 }else if(MemberNumber==""){
         		alert("請輸入廠商統編");
         		addForm.MemberNumber.focus();
             	 return false;
         	 }
         	 return true;
         }
      </script>
   </body>
</html>