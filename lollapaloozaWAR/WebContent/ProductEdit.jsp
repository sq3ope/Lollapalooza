<% 
response.setHeader("Cache-Control","no-cache");
response.addHeader("Cache-Control","no-store");
response.setDateHeader("Expires", 0);
response.setHeader ("Pragma","no-cache"); 
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<f:view>
<head>
	<f:loadBundle var="msg" basename="org.test.messages"/>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-2">
	<title><h:outputText value="#{msg.ProductEdit}"></h:outputText></title>
	<link rel="stylesheet" type="text/css" href="common.css" />
</head>
<body>
	<h:form id="mainForm">
		<h:inputHidden id="productId" value="#{productBean.id}" />
		<h:inputHidden id="deleted" value="#{productBean.deleted}" />	
	
		<h:outputText id="successMessage" value="#{productBean.successMessage}"/>
		<h:outputText id="errorMessage" value="#{productBean.errorMessage}"/>
		<h:messages showDetail="true" showSummary="true" 
				errorClass="errorMessage" infoClass="infoMessage" 
				layout="table"/>
		
		<h:outputLabel rendered="true" value="#{msg.name}"></h:outputLabel>
		<h:inputText value="#{productBean.name}" required="true" tabindex="0"></h:inputText>
		
		<h:commandButton action="#{productBean.save}" value="#{msg.save}"></h:commandButton>
		<h:commandButton action="#{productBean.cancel}" value="#{msg.cancel}"></h:commandButton>
	</h:form>
</body>
</f:view>
</html>