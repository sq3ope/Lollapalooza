<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:view>
	<h:form>
    	<h:outputLabel for="input" value="Enter your name:"/>
    	<h:inputText id="input" value="#{myBean.input}" required="true"/>
    	<h:commandButton value="ClickMe" action="#{myBean.action}"/>
	</h:form>
	<h:outputText value="#{myBean.output}"/> 
</f:view>
</body>
</html>