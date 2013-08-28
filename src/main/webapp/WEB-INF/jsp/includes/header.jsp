<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="top" style="height: 60px;">
	<c:if test="${user != null}">
		Welcome ${user.firstName}
	</c:if>
</div>