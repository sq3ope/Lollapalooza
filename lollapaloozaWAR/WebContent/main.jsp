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
		<h:inputHidden id="transactionId" value="#{productBean.transactionId}" />
	
		<div>
			<h:outputText id="successMessage" value="#{productBean.successMessage}"/>
			<h:outputText id="errorMessage" value="#{productBean.errorMessage}"/>
		</div>
		
		<div>
			<h:commandButton action="#{productBean.add}" value="#{msg.addNewProduct}"></h:commandButton>
			<h:commandButton action="#{productBean.deleteSelected}" value="#{msg.deleteSelectedProducts}"></h:commandButton>
		</div>
		
		<h:dataTable id="dt1" value="#{productBean.products}" var="item" >		
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
		</h:dataTable>
				
	</h:form>
</body>
</f:view>
</html>
