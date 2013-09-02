<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link rel="stylesheet" href="/ExpenseManager/resources/css/nav.css" />
<nav>
	<ul>
		<li><a href="user">User</a></li>
		<li><a href="manageCategory">CATEGORY</a>
			<ul>
				<li><a href="">Income</a></li>
				<li><a href="">Expense</a></li>
			</ul>
		</li>
		<li><a href="report">Report</a></li>
		<li><a href="settings" class="settings">Manage</a>
			<ul>
				<li><a href="" class="settings">Account</a></li>
				<li><a href="" class="settings">Currency</a></li>
				<li><a href="" class="settings">Date</a></li>
				<li><a href="" class="settings">Payment Method</a></li>
				<li><a href="" class="settings">Change Password</a></li>
			</ul>
		</li>
		<li><a href="/ExpenseManager/logout">Log Out</a></li>
	</ul>
</nav>