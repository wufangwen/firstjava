<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
		$("#buy").click(function(){
		var buynumber=$("#buynum").val();
		location.href="${pageContext.request.contextPath}/product?method=addproduct&pid=${product.pid}&buynumber="+buynumber;
		})
	})

</script>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include><br>

	<a href="index.jsp">首页</a>——序号${product.pid }——分类：${product.cid }<br>
				<center >
							<img  src="${pageContext.request.contextPath }/${product.pimage}" width="200px" height="300px"><br>
							产品名称：${product.pname }<br>
								编号：${product.pid }<br>
								<del>市场价：${product.market_price }</del>&nbsp;&nbsp;&nbsp;爆款价<strong style="color: red;"> ${product.shop_price }</strong> <br>
								具体描述：<textarea rows="10" cols="30" >${product.pdesc}</textarea> <input type="text" name="buynum" value="1" id="buynum"><input type="button" value="购买" id="buy" >
				</center>
	
	
	
	


</body>
</html>