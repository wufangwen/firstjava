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
我的订单：<br>

<c:forEach items="${orderList }" var="order">
订单编号：${order.oid } <br>
				
					<table width="500px" border="1px">
		    <tr>
				<td align="center" width="20%">图片</td>
				<td align="center" width="20%">商品</td>
				<td align="center" width="20%">价格</td>
				<td align="center" width="20%">数量</td>
				<td align="center" width="20%">小计</td>

			</tr>
			<c:forEach items="${order.orderItems }" var="orderitem">
			  <tr>
				<td align="center" width="20%"><img src="${pageContext.request.contextPath }/${orderitem.product.pimage}" width="70"
								height="60"></td>
				<td align="center" width="20%"> ${orderitem.product.pname}</td>
				<td align="center" width="20%">${orderitem.product.shop_price}</td>
				<td align="center" width="20%">${orderitem.count}</td>
				<td align="center" width="20%">${orderitem.subtotal}</td>

			</tr>
			</c:forEach>
			</table>
</c:forEach>

</center>
</body>
</html>