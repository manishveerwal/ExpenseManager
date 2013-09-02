<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet"
	href="/ExpenseManager/resources/css/navigation.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/footer.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/form.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<style type="text/css">
label.fieldName {
	margin-top: 20px;
	display: inline-block;
}

#addTransaction {
	margin: 20px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/header.jsp"%>
		<%@include file="includes/navigationHome.jsp"%>
		<div id="addTransaction">
			<form action="saveTransaction" method="post">
				<label class="fieldName" for="date">Date</label><br><input class="fieldSize" name="date" type="date" /><br>
				<label class="fieldName" for="amount">Amount</label><br><input class="fieldSize" name="amount" type="text" /><br>
				<label class="fieldName" for="description">Description</label><br><input class="fieldSize" name="description" type="text" /><br>
				<c:if test="${transactionType == \"Income\"}">
					<label class="fieldName" for="incomeCategory">Category</label><br>
					<select name="category" class="fieldSize">
						<option>Select Category</option>
						<c:forEach items="${incomeCategories}" var="category">
							<option value="${category.categoryId}">${category.categoryName}</option>
						</c:forEach>
					</select>
				</c:if>
				<div class="fieldName">
					<input type="submit" value="Add ${transactionType}" class="button" />
				</div>
			</form>
		</div>
		<%@include file="includes/footer.jsp"%>
	</div>
</body>
</html>