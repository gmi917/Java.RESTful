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
               <h2>帳號資料管理</h2>
               <hr>
                <a href="#" onclick="addAdminMember()">新增帳號資料</a>                             
                  <table class="table table-striped table-bordered table-condensed ">
                     <tr>
                        <td style="font-weight:bold;" class="col-md-1 col-sm-1 text-justify">序號</td>
                        <td style="font-weight:bold;" class="col-md-2 col-sm-2 text-justify">會員帳號</td>
                        <td style="font-weight:bold;" class="col-md-3 col-sm-3 text-justify">會員名字</td>
                        <td style="font-weight:bold;" class="col-md-2 col-sm-2 text-justify">會員角色</td>
                        <td style="font-weight:bold;" class="col-md-2 col-sm-2 text-justify">廠商統編</td>
                        <td style="font-weight:bold;" class="col-md-1 col-sm-1 text-justify"></td>
                        <td style="font-weight:bold;" class="col-md-1 col-sm-1 text-justify"></td>
                     </tr>
                   	 
                     <tr>
                    <form:form id="modifyForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/updateAdminMember" modelAttribute="adminMemberForm" style="font-family:Microsoft JhengHei;font-size:3vh;">                    
                    <c:forEach items="${adminMembers}" var="item"  varStatus="status">
    				<tr>
                    <div class="form-group" >
                        <input type='hidden' id='ContactType' name='ContactType' value='modify'>
                        <td  class="col-md-1 col-sm-1 text-justify">${status.count}</td> 
                        <input type='hidden' name="adminMembers[${status.index}].ID" value="${item.getID()}">                                               
                        <td  class="col-md-2 col-sm-2 text-justify">${item.getMemberID()}</td>
                        <td  class="col-md-2 col-sm-2 text-justify"><div class="col-sm-12"><input type="text" style="text-align: center;"  class="form-control input-lg"  placeholder="會員名字" name="adminMembers[${status.index}].MemberName"  value="${item.getMemberName()}"></div></td>
                        <c:if test="${item.getRole()=='super'}">
                        	<td  class="col-md-1 col-sm-1 text-justify">管理者帳號</div></td>
                        	<td  class="col-md-2 col-sm-2 text-justify"><div class="col-sm-12"><input type="text" style="text-align: center;"  class="form-control input-lg" id="superMemberNumber" placeholder="廠商統編" name="adminMembers[${status.index}].MemberNumber"  value="${item.getMemberNumber()}" onKeyDown="LimitMemberNumberInput(this)" onKeyUp="LimitMemberNumberInput(this)" onkeypress="LimitMemberNumberInput(this)"></div></td>                        
                        </c:if>
                        <c:if test="${item.getRole()=='admin'}">
                        	<td  class="col-md-1 col-sm-1 text-justify">廠商帳號</div></td>
                        	<td  class="col-md-2 col-sm-2 text-justify"><div class="col-sm-12"><input type="text" style="text-align: center;"  class="form-control input-lg" id="MemberNumber" placeholder="廠商統編" name="adminMembers[${status.index}].MemberNumber"  value="${item.getMemberNumber()}" onKeyDown="LimitMemberNumberInput(this)" onKeyUp="LimitMemberNumberInput(this)" onkeypress="LimitMemberNumberInput(this)"></div></td>                        
                        </c:if>
                        
                        <td  class="col-md-1 col-sm-1 text-justify"><a href="#" onclick="saveContact('${item.getRole()}')">儲存</a></td>
                        <td  class="col-md-1 col-sm-1 text-justify"><a href="#" onclick="deleteContact('${item.getID()}')">刪除</a></td>
                	</tr>
					</c:forEach>                   	
					</form:form>
                     </tr>                    					 
                  </table>
               </div>
      <script>  
      function LimitMemberNumberInput(field){ 
    	  maxlimit=8; 
    	  if (field.value.length > maxlimit) 
    	  field.value = field.value.substring(0, maxlimit); 
    	  }
      function addAdminMember(){
    	  window.open("${pageContext.request.contextPath}/adminMember", "_self", "");
      }
      function saveContact(role){    	  
    	  var MemberNumber = document.all("MemberNumber");
    	  var count=0;
    	  if(MemberNumber.length>0){
    		  for(var i=0; i<MemberNumber.length; i++){
    			  if(MemberNumber[i].value==""){
    				  count=count+1;                  
                  }	            	             
              }   
             if(count==0){
            	 var form = document.getElementById("modifyForm").submit();
             }else{
            	 alert("會員角色為廠商帳號的廠商統編不得為空白;請輸入廠商統編"); 
             }
    	  }else{
    		  var form = document.getElementById("modifyForm").submit(); 
    	  }          
      }
      
      function deleteContact(id){
    	  
    	  if(deleteConfirm()){
    	
    	  window.open("${pageContext.request.contextPath}/delAdminMember/"+id, "_self", "");
    	  window.parent.window.alert("刪除完成");
    	  //window.parent.window.open("Manager", "_self", "");
    	  }
    	  
      }
  	function deleteConfirm() {
  		
	　　　　if (window.parent.window.confirm("刪除廠商帳號,也會同時刪除該廠商上架的獎品,你確定要刪除嗎?")) {
					return true;
	　　　　}else{
					return false;
	　　　　} 　
	}
      </script>	
   </body>
</html>