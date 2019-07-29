<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

      </style>
   </head>
   <body  >
               <div class="container-fluid 　" >
               <h2>目前所有獎品</h2>
               <hr>
               <a href="#" onclick="insertPrize()">新增獎品</a>
                  <table class="table table-striped table-bordered table-condensed ">
                     <tr>
                        <td style="font-weight:bold;" class="col-md-1 col-sm-1 text-justify">序號</td>
                        <td style="font-weight:bold;" class="col-md-2 col-sm-2 text-justify">獎品名稱</td>
                        <td style="font-weight:bold;" class="col-md-3 col-sm-3 text-justify">獎品分類</td>
                        <td style="font-weight:bold;" class="col-md-3 col-sm-3 text-justify">獎品狀態</td>
                        <td style="font-weight:bold;" class="col-md-1 col-sm-1 text-justify"></td>
                        <td style="font-weight:bold;" class="col-md-1 col-sm-1 text-justify"></td>
                        <td style="font-weight:bold;" class="col-md-1 col-sm-1 text-justify"></td>
                     </tr>
                     <c:forEach items="${prizes}" var="item"  varStatus="status">
                     <tr>
                        <td  class="col-md-1 col-sm-1 text-justify">${status.count}</td>
                        <input type='hidden' name="prizes[${status.index}].ID" value="${item.getID()}">
                        <td  class="col-md-2 col-sm-2 text-justify">${item.getPrizeName()}</td> 
                        <td  class="col-md-2 col-sm-2 text-justify">${item.getCategoryName()}</td>                                              
                        <c:if test="${item.getState()=='0'}">
                        	<td  class="col-md-2 col-sm-2 text-justify">上架</div></td>                        
                        </c:if>
                        <c:if test="${item.getState()=='1'}">
                        	<td  class="col-md-2 col-sm-2 text-justify">下架</div></td>                        
                        </c:if>                        
                        <td  class="col-md-1 col-sm-1 text-justify"><a href="#" onclick="modifyPrize('${item.getID()}')">修改</a></td>
                        <td  class="col-md-1 col-sm-1 text-justify"><a href="#" onclick="deletePrize('${item.getID()}')">刪除</a></td>
                     	<td  class="col-md-1 col-sm-1 text-justify"><a href="#" onclick="previewPrize('${item.getID()}')">預覽</a></td>
                     
                     </tr>
                     </c:forEach> 
                  </table>
               </div>
      <script>
      function modifyPrize(id){
    	  window.open("${pageContext.request.contextPath}/getPrize/"+id, "_self", "");
      }
      function insertPrize(){
    	  window.open("${pageContext.request.contextPath}/Prize", "_self", "");
      }
      function previewPrize(id){
    	  window.open("${pageContext.request.contextPath}/previewPrize/"+id, "_blank", "");
      }
      function deletePrize(id){
    	  
    	  if(deleteConfirm()){
    	
    	  window.open("${pageContext.request.contextPath}/delPrize/"+id, "_self", "");
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