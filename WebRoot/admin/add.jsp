<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <script src="${pageContext.request.contextPath }/js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript">
  $(function(){
			  var content="";
			      
			   $.post(
			      	"${pageContext.request.contextPath}/admin?method=categorylist",
			     	function(data){
					for(var i=0;i<data.length;i++){
					content+="<option value='"+data[i].cid+"'>"+data[i].cname+"</option>";
					$("#categorylist").html(content);
					} 
				},
				"json" 
			); 
			
		});	   
  </script>
</head>
<body>
 <jsp:include page="/bgheader.jsp"></jsp:include><br>
 <center>
 			<form action="${pageContext.request.contextPath}/admin?method=addproduct" method="post" enctype="multipart/form-data">
 			商品名称：<input type="text" name="pname"><br>
 			市场价格：<input type="text" name="market_price"><br>
 			商城价格:<input type="text" name="shop_price"><br>
 			上传图片：<input type="file" name="upload"><br>
 			所属分类:<select name="cid" id="categorylist" >
 						
 					</select><br>
 			商品描述:<input type="text" name="pdesc"><br>
 			<input type="submit" value="提交" >
 			
 			
 			</form>
 	
 </center>
</body>
</html>