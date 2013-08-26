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
<script type="text/javascript" src="/ExpenseManager/resources/js/jquery-2.0.2.js"></script>
<script type="text/javascript" src="/ExpenseManager/resources/js/signIn.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/header.jsp"%>
		<%@include file="includes/navigationIndex.jsp"%>
		<div id="signInSide">ABOUT WEBSITE</div>
		<div id="signIn">
			<form id="loginForm" action="ExpenseManager/j_spring_security_check" method="post">
				<div class="fieldName">UserName</div>
				<div>
					<input id="email" name="j_username" class="fieldSize" />
				</div>
				<div class="fieldName">Password</div>
				<div>
					<input id="password" name="j_password" class="fieldSize" type="password" />
				</div>
				<div style="color: red;">${singInError}</div>
				<div class="fieldName">
					<input type="submit" name="create" value="Sign Up"
						id="signUpButton" class="button"/>
				</div>
			</form>
		</div>
		<%@include file="includes/footer.jsp"%>
	</div>
</body>
</html>