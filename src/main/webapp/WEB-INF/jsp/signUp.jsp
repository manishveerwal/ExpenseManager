<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet"
	href="/ExpenseManager/resources/css/navigation.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/footer.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/signup.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/form.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/button.css" />
<script type="text/javascript"
	src="/ExpenseManager/resources/js/jquery-2.0.2.js"></script>
<script type="text/javascript"
	src="/ExpenseManager/resources/js/signUp.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Expense Manager</title>
</head>
<body>
	<div id="container">
		<%@include file="includes/header.jsp"%>
		<%@include file="includes/navigationIndex.jsp"%>
		<div id="signupSide">ABOUT WEBSITE</div>
		<div id="signup">
			<fieldset>
				<legend>Sign up</legend>
				<form:form id="accountForm" action="register" method="post"
					modelAttribute="user">
					<div class="fieldName">First Name</div>
					<div>
						<form:input path="firstName" cssClass="fieldSize" autofocus="autofocus"/>
					</div>
					<div class="fieldName">Last Name</div>
					<div>
						<form:input path="lastName" cssClass="fieldSize" />
					</div>
					<div class="fieldName">Email</div>
					<div id="emailError"></div>
					<div>
						<form:input path="email" cssClass="fieldSize" />
					</div>
					<div class="fieldName">Password</div>
					<div>
						<form:input path="password" cssClass="fieldSize" type="password" />
					</div>
					<div class="fieldName">Confirm Password</div>
					<div>
						<form:input path="password1" cssClass="fieldSize" type="password" />
					</div>
					<div class="fieldName">Gender</div>
					<div>
						<select id="gender" name="gender" class="fieldSize">
							<option>Select</option>
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>
					</div>

					<div class="fieldName">Country</div>
					<div>
						<select id="country" name="country" class="fieldSize">
							<option>Select</option>
							<c:forEach items="${countries}" var="location">
								<option value="${location}">${location}</option>
							</c:forEach>
						</select>
					</div>

					<div class="fieldName">
						<input class="button" type="submit" name="create" value="Sign Up" />
					</div>
				</form:form>
			</fieldset>
		</div>
		<%@include file="includes/footer.jsp"%>
	</div>
</body>
</html>