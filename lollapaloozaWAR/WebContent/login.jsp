<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<f:view>
	<head>
		<title>Login</title>
		<meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
		<f:loadBundle var="msg" basename="org.test.messages"/>
	</head>
	<body>
		<h:form>
			<h:messages layout="table"></h:messages>
			<h:panelGrid columns="2">
				<h:outputLabel rendered="true" value="#{msg.name}"></h:outputLabel>
				<h:inputText value="#{loginBean.name}" tabindex="0"></h:inputText>
				<h:outputLabel rendered="true" value="#{msg.password}"></h:outputLabel>
				<h:inputSecret value="#{loginBean.password.convertedId}">
					<f:converter converterId="javax.faces.Long"/>
					<f:validator validatorId="org.test.validatePassword"/>
				</h:inputSecret>				
			</h:panelGrid>		
			<h:commandButton action="login" value="#{msg.login}"></h:commandButton>	
		</h:form>
	</body>
</f:view>
</body>
</html>