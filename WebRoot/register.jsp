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
				document.getElementById("login").onclick= function(){
					location.href="${pageContext.request.contextPath }/login.jsp";
				}
			});
			$(function(){
				$("#username").blur(function(){
		
				var usernameinput=$(this).val();
					$.post(
					"${pageContext.request.contextPath}/checkusername",
						{"username":usernameinput},
						function(data){
						
						var ad=""
						var flags=data.flag;
						if(flags){
						//该用户存在
						ad = "该用户名已经存在";
						$("#ad").css("color","red");
					}else{
					 	  ad = "该用户可以使用";
						$("#ad").css("color","green");
					}
					   $("#ad").html(ad);
						},
						"json"
					);				
				
				
				});
			
			});
 </script>
</head>

<body>
    <jsp:include page="/header.jsp"></jsp:include>
   
<center>
   	<form action="${pageContext.request.contextPath }/product?method=register" method="post"  >

   			姓名：<input type="text" name="username" placeholder="请输入用户名" id="username"><span id="ad"></span><br>
   			密码：<input type="password" name="password" placeholder="请输入密码" ><br>
   			真实姓名：<input type="text" name="name" ><br>
   			email:<input type="text" name="email"><br>
   			电话:<input type="text" name="telephone"><br>
   			性别：<input type="radio" name="sex" value="男">男<input type="radio" name="sex" value="女">女<br>
   			<input type="submit" value="注册">  &nbsp; &nbsp; &nbsp; &nbsp;我有账号快速登陆<input type="button" value="点我"  id="login"><br>
   			
   	</form>
   	</center>
</body>
</html>