<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<script src="js/jquery-1.8.3.js"></script>
</head>
<body>
	<form action="add" id="userForm" method="post">
		<label>姓名：<input type="text" name="name"></label><br>
		<label>密码：<input type="password" name="password"></label><br>
		<label>年龄：<input type="text" name="tAge"></label><br>
		<label>地址：<input type="text" name="address"></label><br>
		<input type="button" value="注册">
	</form>
</body>
<script type="text/javascript">
$("input[type='button']").on("click",function(){
	$("#userForm").submit();
});
</script>
</html>