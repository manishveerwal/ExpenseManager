<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/ExpenseManager/resources/css/container.css" />
<link rel="stylesheet" href="/ExpenseManager/resources/css/signup.css" />
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
		<%@include file="includes/headerIndex.jsp"%>
		<%@include file="includes/navigationIndex.jsp"%>
		<div id="signup">
			<fieldset>
				<form:form id="accountForm" action="register" method="post"
					modelAttribute="user">
					<div>
						<form:input path="firstName" cssClass="fieldSize" placeholder="First Name"/>
						<form:input path="lastName" cssClass="fieldSize" placeholder="Last Name"/>
					</div>
					<div>
						<form:input path="email" cssClass="fieldSize" placeholder="Email" />
						<div id="emailError"></div>
					</div>
					<div>
						<form:input path="password" cssClass="fieldSize" type="password" placeholder="Password" />
					</div>
					<div>
						<form:input path="password1" cssClass="fieldSize" type="password" placeholder="Confirm Password"/>
					</div>
					<div>
						<select id="country" name="country" class="fieldSize" style="color: #999;">
							<option>Your Country</option>
							<c:forEach items="${countries}" var="location">
								<option value="${location}">${location}</option>
							</c:forEach>
						</select>
					</div>
					<div id="gender">
						<label for="gender" class="fieldName">Gender :</label>
						<input id="genderM" type="radio" name="gender" value="M">Male
						<input id="genderF" type="radio" name="gender" value="F">Female
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