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
   			function deleteid(pid){
   			if(confirm("您是否要删除？")){
					location.href="${pageContext.request.contextPath }/product?method=deletepid&pid="+pid;
				}
   		};
   		
  
    $(function(){
   		$("#deleteall").click(function(){
   			if(confirm("您是否要清空购物车？")){
					location.href="${pageContext.request.contextPath }/product?method=clear";
				}
   		});
   		
   });
   function submitcart(){
   			
					location.href="${pageContext.request.contextPath }/user";
				
   		};
   
   </script>
</head>
<body>
             <jsp:include page="/header.jsp"></jsp:include><br>
  <center>
		&nbsp;&nbsp;&nbsp;&nbsp;购物车详情：<br>
		<table width="600px" border="1px">
		<tr>
			<td width="10%" align="center">图片</td>
			<td width="15%" align="center">商品</td>
			<td width="5%" align="center">价格</td>
			<td width="5%" align="center">数量</td>
			<td width="15%" align="center">小计</td>
			<td width="10%" align="center">操作</td>
		</tr>
	<c:forEach items="${cart.cartItems }" var="entry">
		<tr>
			<td width="10%" align="center"><img  src="${pageContext.request.contextPath}/${entry.value.product.pimage}" width="60"></td>
			<td width="15%" align="center"> <a href="${pageContext.request.contextPath}/product?method=productinfo&pid=${entry.value.product.pid}"> ${entry.value.product.pname}</a></td>
			<td width="5%" align="center">${entry.value.product.shop_price}</td>
			<td width="5%" align="center">${entry.value.buyNum }</td>
			<td width="15%" align="center">${entry.value.subtotal }</td>
			<td width="10%" align="center"><a onclick="deleteid(${entry.value.product.pid })">删除</a></td>
		</tr>
	</c:forEach>
		</table>
		&emsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		<c:if test="${empty cart }">
		
		总金额：0元
		</c:if>
		<c:if test="${!empty cart }">
		
				总金额：${cart.total }
		</c:if>

		<input type="button" value="清空购物车" id="deleteall"><input type="submit" value="提交" onclick="submitcart()">
		</center>
</body>
</html>