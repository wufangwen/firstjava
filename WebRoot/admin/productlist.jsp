<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

		function change(pid){
   		
					location.href="${pageContext.request.contextPath }/admin?method=update&pid="+pid;
				
   		};
 

</script>
</head>
<body>
 <jsp:include page="/bgheader.jsp"></jsp:include><br>
 <center>
 商品列表<br>
 <a href="${pageContext.request.contextPath}/admin/add.jsp"><input type="button" align="right" value="添加" ></a>
 <table width="600px" border="1px">
 <tr>
 		<td align="center" width="100px">序号</td>
 		<td align="center" width="100px">商品图片</td>
 		<td align="center" width="100px">商品名称</td>
 		<td align="center" width="100px">商品价格</td>
 		<td align="center" width="100px">编辑</td>
 		<td align="center" width="100px">删除</td>
 		
 </tr>
 <c:forEach items="${list }" var="pro">
 <tr>
 		<td align="center" width="100px">${pro.pid }</td>
 		<td align="center" width="100px"><img src="${pageContext.request.contextPath }/${pro.pimage }" width="70" height="70"> </td>
 		<td align="center" width="100px">${pro.pname }</td>
 		<td align="center" width="100px">${pro.shop_price }</td>
 		<td align="center" width="100px"><input type="button" onclick="change(${pro.pid })" value="编辑"></td>
 		<td align="center" width="100px"><input type="button" onclick="dele()" value="删除"></td>
 		
 </tr>
 </c:forEach>
 
 
 </table>
 
 
 </center>
</body>
</html>