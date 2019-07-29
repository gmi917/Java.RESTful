<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <META   HTTP-EQUIV="Pragma"   CONTENT="no-cache">   
   <META   HTTP-EQUIV="Cache-Control"   CONTENT="no-cache">   
   <META   HTTP-EQUIV="Expires"   CONTENT="0"> 
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
         .medicinePreview {
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
         margin-top:20px;
         margin-right:10px;
         cursor:pointer;
         font-size:15px;
         }
      </style>
   </head>
   <body>
      <div class="container-fluid">
         <div class="row content">
            <div class="col-sm-10">
            <c:forEach items="${prizes}" var="item"  varStatus="status">               
               <hr>                
                  <div class="row" style="display: flex">
                     <label class="control-label col-sm-1" for="CategoryID">獎品分類:</label>
                     <td  class="col-md-2 col-sm-2 text-justify">${item.getCategoryName()}</td>                     
                  </div>
                  <div class="row" style="display: flex">
                     <label class="control-label col-sm-1"  for="PrizeName">獎品名稱:</label>
                     <td  class="col-md-2 col-sm-2 text-justify">${item.getPrizeName()}</td>
                  </div>
                  <div class="row" style="display: flex">
                     <label class="control-label col-sm-1" for="PrizeDescription">獎品描述:</label>  
                     <div class="col-md-4 col-xs-4" style="text-align: justify; text-justify: inter-ideograph;">				
						${item.getPrizeDescription()}
					 </div>		                            
                  </div>
                  <div class="row" style="display: flex">
                     <label class="control-label col-sm-1" for="Point">獎品積分:</label>                     
                     <td  class="col-md-2 col-sm-2 text-justify">${item.getPoint()}</td>                                                                                       
                  </div>
                  <div class="row" style="display: flex">
                     <label class="control-label col-sm-1" for="State">獎品狀態:</label>    
						<c:if test="${item.getState()=='0'}">  
						<td  class="col-md-2 col-sm-2 text-justify">上架</td>                
  						</c:if>
  						<c:if test="${item.getState()=='1'}"> 
  						<td  class="col-md-2 col-sm-2 text-justify">下架</td>                 
  						</c:if>
                  </div>
                  <div class="row" style="display: flex">
                     <label class="control-label col-sm-1" for="Image">獎品圖片:</label>                     
                     <img src="${item.getImage()}"  width="30%" height ="30%">
                        <%-- <div class="medicinePreview" style="background-image:url('${item.getImage()}')"></div> --%>
                        <%-- <input type='hidden' id='Image_old' name='Image_old' value='${item.getImage()}'>
                        <label class="btn btn-primary" >Upload<input type="file" class="uploadFile img" value='${item.getImage()}' name="Image"  style="width: 0px;height: 0px;overflow: hidden;"></label> --%>
                     </div>
                     <!-- col-2 -->
                     <!--<i class="fa fa-plus imgAdd"></i>-->
                  </div>
                  <br>
<%--                   <div class="form-group">
                     <label class="control-label col-sm-3" for="medicine_img">
                        輪播藥材圖片:
                        <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">限定3張；檔案大小限制為600*400、600kb以下且格式為png/jpg</p>
                     </label>
                     <div class="col-sm-2 imgUp" style="height:18vh;width:27vh">
                        <div class="imagePreview" id="imagePreview1" ></div>
                        <input type='hidden' id='slidesImg1_old' name='slidesImg1_old' value='<%=_medicine.getSlidesImg1() %>'>
                        <label class="btn btn-primary" >Upload<input type="file" class="uploadFile img" name="slidesImg1"  style="width: 0px;height: 0px;overflow: hidden;"></label>
                     </div>
                     <!-- col-2 -->
                     <i class="fa fa-plus imgAdd"></i>
                  </div> --%>                   
               </c:forEach>
            </div>
         </div>
      </div>
      <script>
      
      </script>
   </body>
</html>