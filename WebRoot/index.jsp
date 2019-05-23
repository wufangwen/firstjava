<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#header{
			width: 800px	;
			height:880px;
			text-align: center;	
		}
		.root{
			width: 200px;
			height: 220px;
			float: left;
				
		}
	
	</style>
	<script type="text/javascript">
					 $(function(){
			
			     var content="";
			      
			   $.post(
			      	"${pageContext.request.contextPath}/product?method=newproduct",
			     	function(data){
					for(var i=0;i<data.length;i++){
					
					content+="<div class='root' >" +
					"<p><a href='${pageContext.request.contextPath}/product?method=productinfo&pid="+data[i].pid+"'><img  src='${pageContext.request.contextPath }/"+data[i].pimage+"' width='170' height='170'></a></p>"+
					"<p align='center' style='height: 5px'><a href='${pageContext.request.contextPath}/product?method=productinfo&pid="+data[i].pid+"'>"+data[i].pname+"</a></p>"+
					"<p align='center' style='height: 10px'>￥"+data[i].shop_price+"</p>"+
					"</div>";
								
		        	}
					
					$("#header").html(content); 
				},
				"json" 
			);
		});	   
	"<td>"+"&nbsp;"+"<a href=''>"+data[i].cname+"</a>"+"</td>"; 
	
	</script>
  </head>
  
  <body>
      <jsp:include page="/header.jsp"></jsp:include><br>
     <center><font size="7">最新商品如下：</font><br>
		<div id="header" >
		
		</div>
		
	
      </center> 
  </body>
</html>
