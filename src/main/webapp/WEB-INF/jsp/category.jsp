<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/navigation.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/home.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/footer.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/category.css" />
<script type="text/javascript"
	src="/ExpenseManager/resources/js/jquery-2.0.2.js"></script>
<script type="text/javascript"
	src="/ExpenseManager/resources/js/category.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/header.jsp" %>
		<div id="header"><a id="categoryMenu" href="manageCategory">CATEGORY</a><a href="/ExpenseManager/logout" style="margin-left: 700px;">Sign Out</a></div>
		<div id="incomeCat">
			<hr>
			<p>Income Categories</p>
			<table id="incomeTable">
				<c:forEach items="${incomeCategories}" var="incomeCategory">
					<tr>
						<td>${incomeCategory.categoryName}</td>
						<td><a href="deleteIncomeCategory?id=${incomeCategory.categoryId}" class="button">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<form action="createIncomeCategory">
				<input class="textfield" name="incomeCategoryFieldValue" type="text" placeholder="Income Category"/>
				<input class="button" type="submit" value="Add Category" />
			</form>
		</div>
		<div id="expenseCat">
			<hr>
			<p>Expense Categories</p>
			<table id="expenseTable">
				<c:forEach items="${expenseCategories}" var="expenseCategory">
					<tr>
						<td>${expenseCategory.categoryName}</td>
						<td id="${expenseCategory.categoryId}TD"><a id="${expenseCategory.categoryId}A" href="#!" onclick="expand(${expenseCategory.categoryId});"><img alt="" src="/ExpenseManager/resources/img/expand_icon.png"></a></td>
						<td>
							<c:if test="${!empty expenseCategory.categories}">
								<table style="margin-top: 20px; display: none;" id="${expenseCategory.categoryId}">
									<c:forEach items="${expenseCategory.categories}" var="subExpenseCategory">
										<tr>
											<td class="subCatNameTD">${subExpenseCategory.categoryName}</td>
											<td><a href="deleteExpenseCategory?id=${subExpenseCategory.categoryId}&level=2" class="button">Delete</a></td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
						</td>
						<td><a href="deleteExpenseCategory?id=${expenseCategory.categoryId}&level=1" class="button">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<form action="createMainExpenseCategory">
				<input class="textfield" name="expenseCategoryFieldValue" type="text" placeholder="Main Expense Category"/>
				<input class="button" type="submit" value="Add Category" />
			</form>
			<form action="createSubExpenseCategory">
				<select name="mainCategory">
					<option>Select Main Category</option>
					<c:forEach items="${expenseCategories}" var="expenseCategory">
						<option value="${expenseCategory.categoryId}">${expenseCategory.categoryName}</option>
					</c:forEach>
				</select>
				<input id="subCategoryField" name="expenseSubCategoryFieldValue" type="text" placeholder="Expense Sub Category"/>
				<input class="button" type="submit" value="Add Category" />
			</form>
		</div>
		<%@include file="includes/footer.jsp" %>
	</div>
</body>
</html>