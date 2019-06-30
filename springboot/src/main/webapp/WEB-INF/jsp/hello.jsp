<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
<script src="js/jquery-1.8.3.js"></script>
</head>
<body>
	<h2>${now}</h2>
	<h3>用户列表（${map.totalCounts}）</h3>
	<table>
		<tr>
			<td>序号</td>
			<td>姓名</td>
			<td>密码</td>
			<td>年龄</td>
			<td>地址</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${map.list}" var="user" varStatus="index">
			<tr>
				<td>${index.index+1}</td>
				<td>${user.name}</td>
				<td>${user.password}</td>
				<td>${user.tAge}</td>
				<td>${user.address}</td>
				<td>
					<a href="http://127.0.0.1:8082/test/toUpdate?id=${user.id}">编辑</a>&nbsp;&nbsp;&nbsp;
					<a href="http://127.0.0.1:8082/test/delete?id=${user.id}">删除</a>
				</td>
			</tr>
		
		</c:forEach>
	</table>
	<form action="addComment" method="post">
		<input type="text" name="content" placeholder="请输入评论内容">
		<input type="submit" value="发表评论">
	</form>
	<table>
		<c:forEach items="${commentList}" var="comment">
			<tr>
				<td>${comment.name}</td>
				<td>${comment.create_time}</td>
				<td>${comment.content}</td>
				<td>
					<a href="http://127.0.0.1:8082/test/toAdd?id=${comment.id}">回复</a>&nbsp;&nbsp;&nbsp;
					<a href="http://127.0.0.1:8082/test/update?id=${comment.id}">点赞(${comment.like_num})</a>
					
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script type="text/javascript">
	$(function(){
		$("tr:odd").css("background","red");
		$("tr:even").css("background","green");
	});
</script>
</html>