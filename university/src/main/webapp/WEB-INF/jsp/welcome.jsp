<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>University Portal</title>
</head>
<body>
	<style>
		.center{
			text-align:center;
		}
	</style>
	<h1 class="center">University Portal</h1>
	<hr>
	<form method="post" action="/login">
	<div>
		<table>
			<tr><td colspan="2">Login Page</td></tr>
			<tr><td>Username: </td><td><input type="text" name="username"></td></tr>
			<tr><td>Password: </td><td><input type="password" name="password"></td></tr>
			<tr><td colspan="2"><input type="submit" value="Login"></td></tr>
			<tr><td colspan="2">${username_error}</td></tr>
		</table>
	</div>
	
	</form>
</body>
</html>