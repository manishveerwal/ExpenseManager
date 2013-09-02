<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link rel="stylesheet" href="/ExpenseManager/resources/css/nav.css" />
<nav>
	<ul>
		<li><a href="dashboard">DashBoard</a></li>
		<li><a href="report">Report</a>
			<ul>
				<li><a href="">Weekly</a></li>
				<li><a href="">Monthly</a></li>
				<li><a href="">Yearly</a></li>
			</ul>
		</li>
		<li><a href="transaction">Transaction</a>
			<ul>
				<li><a href="addIncome">Add Income</a></li>
				<li><a href="addExpense">Add Expense</a></li>
			</ul>
		</li>
		<li><a href="chart">Chart</a></li>
		<li><a href="settings" class="settings">Settings</a>
			<ul>
				<li><a href="" class="settings">Account</a></li>
				<li><a href="" class="settings">Date</a></li>
				<li><a href="" class="settings">Payment Method</a></li>
				<li><a href="" class="settings">Income Categories</a></li>
				<li><a href="" class="settings">Expense Categories</a></li>
				<li><a href="" class="settings">Change Password</a></li>
			</ul>
		</li>
		<li><a href="/ExpenseManager/logout">Log Out</a></li>
	</ul>
</nav>