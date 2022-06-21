<%@page import="com.meshop.product.entity.Attachment"%>
<%@page import="com.meshop.product.entity.ProductStatus"%>
<%@page import="com.meshop.product.entity.ProductExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	ProductExt product = (ProductExt) request.getAttribute("product");
	List<Attachment> attachList = product.getAttachments();
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/productView.css">
<section id="headerr">
    <section id="imgSection">
    	<div class="productImg" style="background-image: url('<%= request.getContextPath() %>/images/<%= product.getAttachment().getRenamedFilename() %>'); background-size: cover;">
        	<input type="radio" name="slide" id="slide">
	        <div class="bullets">
	        	<label for="slide">&nbsp;</label>
	        </div>
        </div>
    </section>
    <section id="infoSection">
        <div class="view-brand"><%= product.getBrand() %></div>
        <div class="view-title"><%= product.getStatus() == ProductStatus.N ? "[새상품] " : ""%><%= product.getTitle() %></div>
        <div class="view-price">
            <div><%= product.getPrice() %>&nbsp;원</div>
            <button onclick="location.href=''">신고하기</button>
        </div>
        <div class="view-btn">
            <button id="wishBtn">
                <span id="ht">♥</span>
                <span id="ft">&nbsp;찜&nbsp;</span>
            </button>
            <button id="buyBtn">구매하기</button>
        </div>
        <div class="date-place">
            <div>등록일</div>
            <span id="date"><%= product.getRegDate() %></span>
        </div>
        <div class="date-place">
            <div>거래지역</div>
            <span id="view-place">서울시&nbsp;<%= product.getPlace() %></span>
        </div>
        <div class="contentBox">
            <div class="boxTitle">상품정보</div>
            <div id="view-content"><%= product.getContent() %></div>
        </div>
        <div class="storeBox">
            <div class="boxTitle">상점정보</div>
            <div>
                <img src="<%= request.getContextPath() %>/images/user.png" alt="">
                <a href="<%= request.getContextPath() %>/"><%= product.getMember().getStoreName() %></a>
                <div id="starScore"></div>
            </div>
        </div>
    </section>
</section>
<script>
const inputs = Array.from(document.querySelectorAll('input[name=slide]'));
const labels = Array.from(document.querySelectorAll('.bullets label'));

window.onload = () => {
    inputs[0].checked = 'checked';
    labels[0].style.backgroundColor = '#fff';
}

inputs.forEach((input) => {
    input.addEventListener('change', (e) => {
        labels.forEach((label) => {
            label.style.backgroundColor = 'rgba(129, 128, 128, 0.55)'
        });
        for(let i = 0; i < inputs.length; i++) {
            if(inputs[i].checked) labels[i].style.backgroundColor = '#fff';
        }
    });
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>