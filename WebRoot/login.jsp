<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <jsp:include page="/header.jsp"></jsp:include><br>
   		<center>
   		<form action="${pageContext.request.contextPath }/product?method=login" method="post">
   				欢迎登陆网上商城<br>
   				用户名：<input type="text" name="username"><br>
   				密码：<input type="password" name="password"><br>
   				自动登录：<input type="checkbox" name="autologin" value="aotulogin"><br>
   					<input type="submit" value="确定"> &nbsp; &nbsp; &nbsp; &nbsp;<input type="reset" value="重置">
   		
   		</form>
   		</center>
</body>
</html>