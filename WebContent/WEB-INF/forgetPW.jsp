<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UBIR</title>
</head>
<body>
	<form action="Link2find" method="get" enctype="multipart/form-data">
	<p>用户名：<input type = "text" name = "username" value = "${username}" ></p>
	<%-- <p>旧密码：<input type = "text" name = "old_psw" value = "${old_psw}" ></p> --%>
	<p>新密码：<input type = "text" name = "new_psw" value = "${new_psw }"></p>
	<p><input type="submit" value="提交"></p>
	<p><input type = "reset" value = "重设"></p>
	</form>
</body>
</html>