<%@page import="java.util.List"%>
<%@page import="com.meshop.product.entity.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
	List<ProductExt> list = (List<ProductExt>) request.getAttribute("productList");
%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <title>#MESHOP</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/main.css" />
  <script src="https://kit.fontawesome.com/69223d03fa.js" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<!-- 바디 -->
    <div class="banner">

    </div>
    <div class="item-list-wrap">
        <div class="item-list">
        <% for(int i=0; i<list.size(); i++){ 
        	ProductExt product = list.get(i);
        %>
            <div class="item-box">
                <a class="item-inner" href="#">
                    <div class="item">
                        <img src="<%=request.getContextPath() %>/images/<%= product.getAttachment().getOriginalFilename() %>"/>
                        <div class="info-box">
                            <div class="brand">
                                <p><%= product.getBrand() %></p>
                            </div>
                            <p class="product-title"><%=product.getTitle() %></p>
                            <div class="price">
                                <em class="num"> <%=product.getPrice() %>원</em>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <%} %>
        </div>
    </div>
    <div class="btn-section">
        <button class="btn-more">더보기</button>
    </div>

    <footer>

    </footer>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>