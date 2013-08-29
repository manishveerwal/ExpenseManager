<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/navigation.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/footer.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/signIn.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/form.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/headerIndex.jsp"%>
		<%@include file="includes/navigationIndex.jsp"%>
		<div id="signInSide">ABOUT WEBSITE</div>
		<div id="signIn">
			<div style="font-size: medium; margin: 0px 0px 30px 0px; color: #025928; font-weight: bold;">Sign in</div>
			<form id="loginForm" action="ExpenseManager/j_spring_security_check" method="post">
				<div>
					<label class="fieldName" for="j_username">Username</label>
					<input id="email" name="j_username" class="fieldSize" autofocus="autofocus" />
				</div>
				<div>
					<label class="fieldName" for="j_password">Password</label>
					<input id="password" name="j_password" class="fieldSize" type="password" />
				</div>
				<div style="color: red;">${singInError}</div>
				<div class="fieldName">
					<input class="button" type="submit" name="create" value="Sign in"/>
				</div>
			</form>
		</div>
		<%@include file="includes/footer.jsp"%>
	</div>
</body>
</html>