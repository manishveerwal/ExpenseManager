<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/form.css" />
<script type="text/javascript"
	src="/ExpenseManager/resources/js/jquery-2.0.2.js"></script>
<script type="text/javascript"
	src="/ExpenseManager/resources/js/feedback.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/headerIndex.jsp"%>
		<%@include file="includes/navigationIndex.jsp"%>
		<div id="feedbackDiv" style="padding: 30px;">
			<div style="color: green;">${msg}</div>
			<div style="color: red;">${feedbackError}</div>
			<form:form id="feedback" action="postFeedback" method="post"
				modelAttribute="feedback">
				<div>
					<label for="name" class="fieldName">Name</label><br>
					<form:input path="name" cssClass="fieldSize" />
				</div>
				<div>
					<label for="email" class="fieldName">Email</label><br>
					<form:input path="email" cssClass="fieldSize" />
					<div id="emailError"></div>
				</div>
				<div>
					<label for="comment" class="fieldName">Comment</label><br>
					<textarea id="textarea" name="comment" rows="10" cols="50"></textarea>
				</div>
				<div class="fieldName">
					<input class="button" type="submit" name="create" value="Submit" />
				</div>
			</form:form>
		</div>
		<%@include file="includes/footer.jsp"%>
	</div>
</body>
</html>