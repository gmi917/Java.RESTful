<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
         margin-top:0px;
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
               <h2>獎品資料管理>新增</h2>
               <hr>
               <form name="reg" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/addPrize" enctype="multipart/form-data" style="font-family:Microsoft JhengHei;font-size:3vh;">
                  <div class="form-group" >
                     <label class="control-label col-sm-3"  for="CategoryID"><font color="red">*</font>獎品分類:</label>
                     <div class="col-sm-9">
                     <select name='CategoryID'>
                     <option value="">請先選擇獎品分類</option>
                        <c:forEach var="item" items="${categorys}">            				
            				<option value="${item.getID()}">${item.getCategoryName()}</option>
       					</c:forEach>
       					</select>
                     </div>
                  </div>
                  <div class="form-group" >
                     <label class="control-label col-sm-3"  for="PrizeName"><font color="red">*</font>獎品名稱:
                     <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">字數限制20個字</p>
                     </label>
                     <div class="col-sm-9">
                        <input type="text" class="form-control input-lg" id="PrizeName" placeholder="請輸入獎品名稱" name="PrizeName" onKeyDown="LimitPrizeNmaeInput(this)" onKeyUp="LimitPrizeNmaeInput(this)" onkeypress="LimitPrizeNmaeInput(this)">
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-3" for="PrizeDescription"><font color="red">*</font>獎品描述:
                     <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">字數限制200個字</p>
                     </label>
                     <div class="col-sm-9">          
                        
                        <textarea class="form-control" rows="4" id= "PrizeDescription" placeholder="請輸入獎品描述" name="PrizeDescription" onKeyDown="LimitTextArea(this)" onKeyUp="LimitTextArea(this)" onkeypress="LimitTextArea(this)"></textarea>
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-3" for="Point"><font color="red">*</font>獎品積分:</label>
                     <div class="col-sm-9">          
                   <input type="text" class="form-control input-lg" id="Point" placeholder="請輸入獎品積分" name="Point" onkeyup="this.value=this.value.replace(/[^\d]/g,'')">
                     </div>
                  </div>
                  <div class="form-group">
                     <label class="control-label col-sm-3" for="State"><font color="red">*</font>獎品狀態:</label>
                     <div class="col-sm-9">          
                        <input type="radio" name="State" value="0" checked>上架<br>
  						<input type="radio" name="State" value="1" >下架<br>
                     </div>
                  </div>                                    
                 <div class="form-group">
                     <label class="control-label col-sm-3" for="images">
                                                     <font color="red">*</font>獎品圖片:
                        <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">檔案大小限制為600*480、600kb以下且格式為png/jpg</p>
                     </label>
                     <div class="col-sm-2 imgUp" style="height:18vh;width:27vh">
                        <div class="medicinePreview" ></div>
                        <label class="btn btn-primary" >Upload<input type="file" class="uploadFile img" id="images" name="images" multiple="multiple" value="Upload Photo" style="width: 0px;height: 0px;overflow: hidden;"></label>
                     </div>
                     <!-- col-2 -->
                     <!--<i class="fa fa-plus imgAdd"></i>-->
                  </div>
                  <br>
            <!--       <div class="form-group">
                     <label class="control-label col-sm-3" for="medicine_img">
                                                     輪播藥材圖片:
                        <p style="font-family:Microsoft JhengHei;font-size:1vh;color:red">限定3張；檔案大小限制為600*400、600kb以下且格式為png/jpg</p>
                     </label>
                     <div class="col-sm-2 imgUp" style="height:18vh;width:27vh">
                        <div class="imagePreview"></div>
                        <label class="btn btn-primary" >Upload<input type="file" class="uploadFile img" name="slidesImg1" value="Upload Photo" style="width: 0px;height: 0px;overflow: hidden;"></label>
                     </div>
                     col-2
                     <i class="fa fa-plus imgAdd"></i>
                  </div> -->
                  <div class="form-group text-right">
                     <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" onClick="check(); return false;">儲存</button>
                     </div>
                  </div>
               </form>
            </div>
         </div>
      </div>
       
      <script>
      function LimitPrizeNmaeInput(field){ 
    	  maxlimit=20; 
    	  if (field.value.length > maxlimit) 
    	  field.value = field.value.substring(0, maxlimit); 
    	  }
      function LimitTextArea(field){ 
    	  maxlimit=200; 
    	  if (field.value.length > maxlimit) 
    	  field.value = field.value.substring(0, maxlimit); 
    	  }
      function check()
      {              
    	  var file1=window.document.reg.images;
              if(reg.CategoryID.value == ""){
                      alert("未選擇獎品分類");                      
              }else if(reg.PrizeName.value == ""){
                      alert("未輸入獎品名稱");
              }else if(reg.PrizeDescription.value == ""){
                       alert("未輸入獎品描述");
              }else if(reg.Point.value == ""){
            	  alert("未輸入獎品積分");
              }else if(file1.value==''){
          		alert('請選擇檔案');
          	  }else{
            	  reg.submit();
              }
       }
      var img_num = 2;
         $(".imgAdd").click(function(){
        	 if(img_num==4){
        		 alert("最多只能新增3張照片~");
        	 }else{
        		 $(this).closest(".row").find('.imgAdd').before('<div class="col-sm-2 imgUp" style="height:18vh;width:27vh"><div class="imagePreview"></div><label class="btn btn-primary">Upload<input type="file" class="uploadFile img" '+ 'name="slidesImg'+img_num+ '" value="Upload Photo" style="width:0px;height:0px;overflow:hidden;"></label><i class="fa fa-times del"></i></div>');
                 img_num++;
        	 }
     	    
         });
         $(document).on("click", "i.del" , function() {
         	$(this).parent().remove();
         	img_num--;
         });
         $(function() {
             $(document).on("change",".uploadFile", function()
             {
                 var uploadFile = $(this);                                  
                 var files = !!this.files ? this.files : [];
                 if (!files.length || !window.FileReader) return; // no file selected, or no FileReader support
                                 
                 if ((( files[0].type)=='image/png'||( files[0].type)=='image/jpeg') &&　(files[0].size)<1000001){ // only image file
 
                     var reader = new FileReader(); // instance of the FileReader
                     var img = new Image();
                     reader.readAsDataURL(files[0]); // read the local file
          
                     reader.onloadend = function(e){ // set image data as background of div
                         //alert(uploadFile.closest(".upimage").find('.imagePreview').length);
                    	 img.src = e.target.result;
                    	 img.onload = function() {
                    		 console.log(this.width);
                 		    console.log(this.height);
                    		 if(this.width == 600 && this.height == 480){
                    			 uploadFile.closest(".imgUp").find('.medicinePreview').css("background-image", "url("+e.target.result+")");
                    		 }else{
                    			alert("上傳的圖檔尺寸不符合格式");}
                    		}
                     }
                 }else{
                	 alert("上傳的圖檔不符合格式");
                 }
             });
         });
      </script>
   </body>
</html>