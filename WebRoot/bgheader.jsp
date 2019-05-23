<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<p>欢迎来到商城后台系统</p><br>
		欢迎，${admin.adminname } &nbsp; &nbsp; &nbsp;<a href="">分类管理</a>  <a href="${pageContext.request.contextPath }/admin?method=productlist">商品管理</a>  <a href="">订单管理</a> 


</center>
</body>
</html>