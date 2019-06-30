<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script src="js/jquery-1.8.3.js"></script>
</head>
<body>
	<span id="msg"></span><br>
	<label>姓名:<input type="text" name="name" id="name" placeholder="请输入用户名"></label><br>
	<label>密码:<input type="password" name="password" id="password" placeholder="请输入密码"></label><br>
	<input type="button" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://127.0.0.1:8082/test/register"><input type="button" value="注册"></a>
</body>
<script type="text/javascript">
	
	$("input[type='button']").on("click",function(){
		var name = $("#name").val();
		var password = $("#password").val();
		$.ajax({
			   type: "POST",
			   url: "login",
			   data: "name="+name+"&password="+password,
			   success: function(msg){
			     if(msg=="nameError"){
			    	 $("#msg").text("用户名不存在").css("color","red");
			     }else if(msg=="passwordError"){
			    	 $("#msg").text("密码不正确").css("color","red");
			     }else{
			    	 window.location.href="http://127.0.0.1:8082/test/index";
			     }
			   }
			});
	});
</script>
</html>