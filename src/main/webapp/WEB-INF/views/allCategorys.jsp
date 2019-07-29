<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
         .symptomPreview {
         /*width:  52vh;
         height: 13vh;*/
         width:  100%;
         height: 100%;
         background-position: center center;
         background:url(img/medicine_preview_2.jpg);
         background-color:#fff;
         background-size: 100% 100%;
         background-repeat:no-repeat;
         display: inline-block;
         box-shadow:0px -3px 6px 2px rgba(0,0,0,0.2);
         }
         .imagePreview {
         /*width:  22.5vh;
         height: 15vh;*/
         width:  100%;
         height: 100%;
         background-position: center center;
         background:url(img/noimage.jpg);
         background-color:#fff;
         background-size: cover;
         background-repeat:no-repeat;
         display: inline-block;
         box-shadow:0px -3px 6px 2px rgba(0,0,0,0.2);
         }
         .btn-primary
         {
         display:block;
         border-radius:0px;
         box-shadow:0px 4px 6px 2px rgba(0,0,0,0.2);
         /*width: 22.5vh;*/
         }
         .imgUp
         {
         margin-bottom:40px;
         }
         .del
         {
         position:absolute;
         top:0px;
         right:15px;
         width:30px;
         height:30px;
         text-align:center;
         line-height:30px;
         background-color:rgba(255,255,255,0.6);
         cursor:pointer;
         }
         .imgAdd
         {
         width:30px;
         height:30px;
         border-radius:50%;
         background-color:#4bd7ef;
         color:#fff;
         box-shadow:0px 0px 2px 1px rgba(0,0,0,0.2);
         text-align:center;
         line-height:30px;
         margin-top:0px;
         margin-right:10px;
         cursor:pointer;
         font-size:15px;
         }
      </style>
   </head>
   <body  >
      <div class="container-fluid 　" >
         <h2>獎品分類資料管理</h2>
         <hr>
        <div id="subContent" >
         <form id="insertForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/addCategory" style="font-family:Microsoft JhengHei;font-size:3vh;">
            <input type='hidden' id='SymptomType' name='SymptomType' value='insert'>
            <div class="form-group" >
               <label class="control-label col-sm-2"  for="symptom">獎品分類名稱:
               <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">字數限制8個字</p>
               </label>
               <div class="col-sm-4">               
                  <input type="text" class="form-control " id="CategoryName" placeholder="請輸入獎品分類名稱" name="CategoryName" onKeyDown="LimitCategoryNameInput(this)" onKeyUp="LimitCategoryNameInput(this)" onkeypress="LimitCategoryNameInput(this)" style="text-align: center;">
               </div>
            </div>
            <div class="form-group text-right">
               <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-default">新增</button>
               </div>
            </div>
         </form>
         <!--  <a href="#" onclick="insertMedicine()">新增藥材</a> -->
         <table class="table table-striped table-bordered table-condensed ">
            <tr>
               <td style="font-weight:bold;" class="col-md-1 col-sm-1 text-justify">序號</td>
               <td style="font-weight:bold;" class="col-md-3 col-sm-3 text-justify">獎品分類名稱</td>
               <td style="font-weight:bold;" class="col-md-2 col-sm-2 text-justify"></td>
               <td style="font-weight:bold;" class="col-md-2 col-sm-2 text-justify"></td>
            </tr>
            <tr>
               <form id="modifyForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/updateCategory" modelAttribute="categoryForm" style="font-family:Microsoft JhengHei;font-size:3vh;">
				 <c:forEach items="${categorys}" var="item"  varStatus="status">
				  <tr>                  
                  <div class="form-group" >
                     <input type='hidden' id='ContactType' name='ContactType' value='modify'>
                     <td  class="col-md-1 col-sm-1 text-justify">${status.count}</td>
                     <input type='hidden' name="categorys[${status.index}].ID" value="${item.getID()}">
                     <td  class="col-md-3 col-sm-3 text-justify"><div class="col-sm-12"><input type="text" style="text-align: center;"  class="form-control input-lg"  placeholder="獎品分類名稱" name="categorys[${status.index}].CategoryName"  value="${item.getCategoryName()}" onKeyDown="LimitCategoryNameInput(this)" onKeyUp="LimitCategoryNameInput(this)" onkeypress="LimitCategoryNameInput(this)"></div></td>                                           
                  </div>
                  </td>
                  <td  class="col-md-2 col-sm-2 text-justify"><a href="#" onclick="saveContact(id)">儲存</a></td>
                  <td  class="col-md-2 col-sm-2 text-justify"><a href="#" onclick="deleteContact('${item.getID()}')">刪除</a></td>
				</tr>
               </c:forEach>
               </form>
            </tr>
         </table>
      </div>
      </div>
      <script>    
      function LimitCategoryNameInput(field){ 
    	  maxlimit=8; 
    	  if (field.value.length > maxlimit) 
    	  field.value = field.value.substring(0, maxlimit); 
    	  }
         function saveContact(id){
          var form = document.getElementById("modifyForm").submit();
         }
         
         
         function deleteContact(id){
          
          if(deleteConfirm()){
         
          window.open("${pageContext.request.contextPath}/delCategory/"+id, "_self", "");
          window.parent.window.alert("刪除完成");
          //window.parent.window.open("Manager", "_self", "");
          }
          
         }
         function deleteConfirm() {
         
         　　　　if (window.parent.window.confirm("你確定要刪除嗎?")) {
         return true;
         　　　　}else{
         return false;
         　　　　} 　
         }
      </script>	
   </body>
</html>