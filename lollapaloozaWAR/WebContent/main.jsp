<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<f:view>
<head>
	<f:loadBundle var="msg" basename="org.test.messages"/>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<h:form>
		<h:commandButton action="addProduct" value="#{msg.addProduct}"></h:commandButton>
		<h:commandButton action="editProduct" value="#{msg.editProduct}"></h:commandButton>
		<h:commandButton action="deleteProduct" value="#{msg.deleteProduct}"></h:commandButton>
	</h:form>
</body>
</f:view>
</html>