<%@page import="com.meshop.product.entity.ProductExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	ProductExt product = (ProductExt) request.getAttribute("product");
%>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>