<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <script src="js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			function away() {
				if(confirm("您是否要退出？")){
					location.href="${pageContext.request.contextPath }/product?method=away";
				}
			}	  
				 $(function(){
				
			    var content="<tr>";
			      
			   $.post(
			      	"${pageContext.request.contextPath}/product?method=categorylist",
			     	function(data){
					for(var i=0;i<data.length;i++){
					content+="<td>"+"&nbsp;"+"<a href='${pageContext.request.contextPath}/product?method=findproductbyid&cid="+data[i].cid+"'>"+data[i].cname+"</a>"+"</td>"; 
					}
					content+="</tr>";
					$("#categorylist").html(content); 
				},
				"json" 
			);
		});	   
		function searchword(obj){
	          	
						var word = $(obj).val();
						
						var content = "";
						
						$.post(
							"${pageContext.request.contextPath}/product?method=searchword",
							{"word":word},
							function(data){
								
								if(data.length>0){
									for(var i=0;i<data.length;i++){
										content+="<div>"+data[i]+"</div>";
									}
									$("#show").html(content);
									$("#show").css("display","block");
								}
								
							},
							"json"
						);
						
					}
					
					$(function(){
						$("#search").blur(function(){
						var word ="";
						var content ="";
						$.post(
							"${pageContext.request.contextPath}/product?method=clearword",
							{"word":word},
							function(data){
								
								
										content+="<div style='padding:5px;cursor:pointer'>"+data+"</div>";
									
									$("#show").html(content);
									$("#show").css("display","block");
								
								
							},
							"json"
						);
						 
						});				
					
					})
			</script>
		
</head>
<body>

		
		<center>
		
				 欢迎来到网上商城  
				    &nbsp; &nbsp; &nbsp;
				    <c:if test="${empty user}">
				    游客你好，
				      <a href="register.jsp"> 注册  </a> 
				    <a href="login.jsp"> 登录      </a>
				    <a href="index.jsp">首页</a>
				    <a href="${pageContext.request.contextPath}/cart.jsp">购物车 </a>
				     <a href="${pageContext.request.contextPath}/myorder">我的订单 </a>
				    </c:if>
				      <c:if test="${!empty user}">
				                       欢迎 ，${user.username } 
				    <a ></a>
				    
				    <a href="register.jsp"> 注册  </a>
				    <a onclick="away()">退出</a>
				    <a href="index.jsp">首页</a>
				    <a href="${pageContext.request.contextPath}/cart.jsp">购物车 </a>
				     <a href="${pageContext.request.contextPath}/myorder">我的订单 </a>
				    </c:if>
				    
				  		<table id="categorylist">
				  			
						
				  		</table><br>
				  		<form action="">
				  		<input type="text" id="search" onkeyup="searchword(this)" onblur="blur()">
				  		<div id="show">
				  		
				  		</div>
				  		</form>
				  		
	</center>
	
</body>
</html>