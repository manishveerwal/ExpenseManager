<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/navigation.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/footer.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/home.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/header.jsp" %>
		<%@include file="includes/navigationHome.jsp" %>
		<div id="summary" style="height: 400px; text-align: center;">
			<div style="font-weight: bolder; font-size: xx-large;">Account Details</div>
			<div class="summary">Total Expense: ${totalExpense}</div>
			<div class="summary">Total Income: ${totalIncome}</div>
			<div>
				<a class="button" href="addIncome">Add Income</a> <a class="button" href="addExpense">Add Expense</a>
			</div>
		</div>
		<%@include file="includes/footer.jsp" %>
	</div>
</body>
</html>