<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<f:view>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-2">
			<title>Welcome</title>
			<f:loadBundle var="msg" basename="org.test.messages"/>
		</head>
		<body>
			<h:form>
				<h:outputLabel value="#{msg.welcome} #{loginBean.name}"></h:outputLabel>
			</h:form>		
		</body>
	</f:view>
</html>