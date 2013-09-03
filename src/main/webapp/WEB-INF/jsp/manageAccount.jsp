<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/form.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<style type="text/css">
	#accountListDiv{
		margin-bottom: 30px;
		margin-top: 30px;
	}
	
	td{
		padding: 5px;
	}
	
	a.button{
		font-size: x-small; 
		padding: 2px;
	}
	
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/header.jsp"%>
		<%@include file="includes/navigationHome.jsp"%>
		<div id="manageAccount">
			<div id="accountListDiv">
				<div style="color: red;">${accountError}</div>
				<table>
					<thead>
						<tr>
							<td  style="border-bottom: 1px solid #303030; text-align: center;">Accounts</td>
						</tr>
					</thead>
					<c:forEach items="${accounts}" var="account">
						<tr>
							<td style="font-size: small;">${account.accountName}</td>
							<td><a href="" class="button">Edit</a></td>
							<td><a href="deleteAccount?accountId=${account.accountId}" class="button">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<form action="addAccount" method="post">
				<input name="account" type="text" class="fieldSize" placeholder="Account Name"/>
				<input type="submit" value="Create" class="button"/>
			</form>
		</div>
		<%@include file="includes/footer.jsp"%>
	</div>
</body>
</html>