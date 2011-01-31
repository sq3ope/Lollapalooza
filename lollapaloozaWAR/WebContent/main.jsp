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
	<link rel="stylesheet" type="text/css" href="common.css" />
</head>
<body>
	<h:form id="mainForm">
		<h:outputText id="successMessage" value="#{productBean.successMessage}"/>
		<h:outputText id="errorMessage" value="#{productBean.errorMessage}"/>
		
		<h:commandButton action="#{productBean.add}" value="#{msg.addNewProduct}"></h:commandButton>
		<h:commandButton action="#{productBean.deleteSelected}" value="#{msg.deleteSelectedProducts}"></h:commandButton>
		
	<h:dataTable id="dt1" value="#{productBean.products}" var="item" bgcolor="#F1F1F1" border="10" cellpadding="5" cellspacing="3" width="50%" dir="LTR" frame="hsides" rules="all" summary="This is a JSF code to create dataTable." >
			<f:facet name="header">
				<h:outputText value="This is 'dataTable' demo" />
			</f:facet>
			
			<h:column>
				<h:selectBooleanCheckbox value="#{item.selected}" />
			</h:column>
			
			<h:column>
				<h:commandButton value="#{msg.edit}" action="#{productBean.edit}"> 
					<f:setPropertyActionListener 
						target="#{productBean.selectedProduct}" 
						value="#{item}" /> 
				</h:commandButton>
			</h:column>
			
			<h:column>
				<f:facet name="header">
					<h:outputText value="ID" />
				</f:facet> 
				<h:outputText style="" value="#{item.id}" ></h:outputText>
			</h:column>
			
			<h:column>
				<f:facet name="header">
					<h:outputText value="Name"/>
				</f:facet> 
				<h:outputText value="#{item.name}"></h:outputText>
			</h:column>
							
			<f:facet name="footer">
				<h:outputText value="The End" />
			</f:facet> 		
		</h:dataTable>
				
	</h:form>
</body>
</f:view>
</html>
