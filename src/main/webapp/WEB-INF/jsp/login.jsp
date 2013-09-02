<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/form.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
<style type="text/css">
	#signIn{
 		margin: 90px 700px 90px 0px;
		padding: 50px;
		font-size: small;
		border: thin solid #303030;
	}
</style>
</head>
<body>
	<div id="container">
		<%@include file="includes/headerIndex.jsp"%>
		<%@include file="includes/navigationIndex.jsp"%>
		<div id="signIn">
			<div style="color: red; margin-bottom: 10px;">${singInError}</div>
			<form id="loginForm" action="ExpenseManager/j_spring_security_check" method="post">
				<div>
					<label class="fieldName" for="j_username">Username</label><br>
					<input id="email" name="j_username" class="fieldSize" autofocus="autofocus" />
				</div>
				<div>
					<label class="fieldName" for="j_password">Password</label><br>
					<input id="password" name="j_password" class="fieldSize" type="password" />
				</div>
				<div class="fieldName">
					<input class="button" type="submit" name="create" value="Sign in"/>
				</div>
			</form>
		</div>
		<%@include file="includes/footer.jsp"%>
	</div>
</body>
</html>