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
		<div id="summary">
			<div style="font-weight: bold; font-size: large; text-align: center; color: #FF6600; margin-top: 20px;">Account Summary</div>
			<hr>
				<label class="label">Expense Summary</label>		
			<hr>
			<table>
				<tr>
					<td class="summary">Today Expense</td>
					<td>:</td>
					<td class="expenseValue">${todayExpense}</td>
				</tr>
				<tr>
					<td class="summary">Current Week Expense</td>
					<td>:</td>
					<td class="expenseValue">${weekExpense}</td>
				</tr>
				<tr>
					<td class="summary">Current Month Expense</td>
					<td>:</td>
					<td class="expenseValue">${monthExpense}</td>
				</tr>
				<tr>
					<td class="summary">Current Year Expense</td>
					<td>:</td>
					<td class="expenseValue">${yearExpense}</td>
				</tr>
				<tr>
					<td class="summary">Total Expense</td>
					<td>:</td>
					<td class="expenseValue">${totalExpense}</td>
				</tr>
			</table>
			<hr style="margin-top: 50px;">
				<label class="label">Income Summary</label>		
			<hr>
			<table>
				<tr>
					<td class="summary">Today Income</td>
					<td>:</td>
					<td class="incomeValue">${todayIncome}</td>
				</tr>
				<tr>
					<td class="summary">Current Week Income</td>
					<td>:</td>
					<td class="incomeValue">${weekIncome}</td>
				</tr>
				<tr>
					<td class="summary">Current Month Income</td>
					<td>:</td>
					<td class="incomeValue">${monthIncome}</td>
				</tr>
				<tr>
					<td class="summary">Current Year Income</td>
					<td>:</td>
					<td class="incomeValue">${yearIncome}</td>
				</tr>
				<tr>
					<td class="summary">Total Income</td>
					<td>:</td>
					<td class="incomeValue">${totalIncome}</td>
				</tr>
			</table>
			<br><br>
			<hr>
				<table>
					<tr>
						<td class="summary">Balance</td>
						<td>:</td>
						<td style="color: green;" class="value">${balance}</td>
					</tr>
				</table>
			<hr>
			<div style="margin-top:  30px;">
				<a class="button" href="addIncome">Add Income</a> <a class="button" href="addExpense">Add Expense</a>
			</div>
		</div>
		<%@include file="includes/footer.jsp" %>
	</div>
</body>
</html>