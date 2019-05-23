<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
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
</head>
<body>
   <jsp:include page="/header.jsp"></jsp:include><br>
   <center>
		<div id="header" >
		<c:forEach items="${list}" var="pro">
		<div class='root' >
		<p><a href="${pageContext.request.contextPath}/product?method=productinfo&pid=${pro.pid}"><img  src="${pageContext.request.contextPath }/${pro.pimage}" width="170" height="170"></a></p>
	    <p align="center" style="height: 5px"><a href="${pageContext.request.contextPath}/product?method=productinfo&pid=${pro.pid}">${pro.pname }</a></p>
		<p align="center" style="height: 10px">${pro.shop_price }</p>
		</div>
		</c:forEach> 
		</div>
		
	
      </center> 
</body>
</html>