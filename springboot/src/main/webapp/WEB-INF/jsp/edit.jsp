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
	<input type="hidden" name="id" value="${user.id}">
	<label>姓名：<input type="text" name="name" readonly="readonly" value="${user.name}"></label><br>
	<label>年龄：<input type="text" name="tAge" id="tAge" value="${user.tAge}"></label><br>
	<label>地址：<input type="text" name="address" id="address" value="${user.address}"></label><br>
	<input type="button" value="修改">
</body>
<script type="text/javascript">
$("input[type='button']").on("click",function(){
	var id = $("input[type='hidden']").val();
	var tAge = $("#tAge").val();
	var address = $("#address").val();
	alert(id);
	$.ajax({
		   type: "POST",
		   url: "edit",
		   data: "id="+id+"&tAge="+tAge+"&address="+address,
		   success: function(msg){
		     if(msg=="success"){
		    	 window.location.href="http://127.0.0.1:8082/test/index";
		     }
		   }
		});
});
</script>
</html>