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
<jsp:include page="/header.jsp"></jsp:include><br>
<center>
		订单详情：<br>
		<table width="500px" border="1px">
		    <tr>
				<td align="center" width="20%">图片</td>
				<td align="center" width="20%">商品</td>
				<td align="center" width="20%">价格</td>
				<td align="center" width="20%">数量</td>
				<td align="center" width="20%">小计</td>

			</tr>
			<c:forEach items="${order.orderItems }" var="orderItem">
			   <tr>
				<td align="center" width="20%" ><img src="${pageContext.request.contextPath }/${orderItem.product.pimage}"height="100px"></td>
				<td align="center" width="20%" height="100px">${orderItem.product.pname}</td>
				<td align="center" width="20%" height="100px">${orderItem.product.shop_price}</td>
				<td align="center" width="20%" height="100px">${orderItem.count }</td>
				<td align="center" width="20%" height="100px">${orderItem.subtotal }</td>

			</tr>
		</c:forEach>
		
		</table>
	商品总金额：${order.total }元<br>
	<form action="${pageContext.request.contextPath }/product?method=updateorderitem" method="post">
	<input type="hidden" name="oid" value="${order.oid }"><!-- 将OID通过表单提交 -->
		地址：<input type="text" name="address"><br>
		收货人:<input type="text" name="name"><br>
		电话:<input type="text" name="telephone"><br>
		<input type="submit" value="提交"><br>
</form>
				
</center>
</body>
</html>