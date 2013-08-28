<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/navigation.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/footer.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/form.css" />
	<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<style type="text/css">
	label.fieldName{
		display: block;
	}
	
	#addTransaction{
		margin: 10px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/header.jsp" %>
		<%@include file="includes/navigationHome.jsp" %>
		<div id="addTransaction">
			<form action="saveTransaction" method="post">
				<fieldset>
					<legend>Add ${transactionType}</legend>
					<label class="fieldName">Date</label><input class="fieldSize" name="date" type="date" />
					<label class="fieldName">Amount</label><input class="fieldSize" name="amount" type="text"/>
					<label class="fieldName">Description</label><input class="fieldSize" name="description" type="text"/>
					<input name="category" type="hidden" value="${transactionType}"/>
					<div class="fieldName"><input type="submit" value="Add ${transactionType}" class="button"/></div>
				</fieldset>
			</form>
		</div>
		<%@include file="includes/footer.jsp" %>
	</div>
</body>
</html>