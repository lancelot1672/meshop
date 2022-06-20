<%@page import="java.util.List"%>
<%@page import="java.text.*"%>
<%@page import="com.meshop.product.entity.*"%>
<%@page import="com.meshop.member.entity.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
	List<ProductExt> list = (List<ProductExt>) request.getAttribute("productList");
	DecimalFormat df = new DecimalFormat("###,###");
%>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<!-- 바디 -->
    <div class="banner">

    </div>
    <div class="product_section_title">
        <div class="title">
            판매 중인 상품
        </div>
    </div>
    <div class="item-list-wrap">
        <div class="item-list">
        <% for(int i=0; i<4; i++){ 
        	ProductExt product = list.get(i);
        %>
            <div class="item-box">
            <% if(loginMember != null && wishList.contains(product.getProductId())){ %>
            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=product.getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-solid fa-heart"></i></button>
            <% }else if(loginMember != null){ %>
            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=product.getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-regular fa-heart"></i></button>
            <% }else{ %>
            	<button class="wish-btn" onclick="location.href='<%=request.getContextPath() %>/member/login';"><i class="fa-regular fa-heart"></i></button>
            <% } %>
                <a class="item-inner" href="<%=request.getContextPath()%>/product/productId=<%=product.getProductId() %>">
                    <div class="item">
                        <img src="<%=request.getContextPath() %>/images/<%= product.getAttachment().getRenamedFilename() %>"/>
          		
                        <div class="info-box">
                            <div class="brand">
                                <p><%= product.getBrand() %></p>
                            </div>
                            <p class="product-title"><%=product.getTitle() %></p>
                            <div class="price">
                                <em class="num"> <%=df.format(product.getPrice()) %>원</em>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <%} %>
        </div>
        <div class="item-list">
        <% for(int i=2; i<6; i++){ 
        	ProductExt product = list.get(i);
        %>
            <div class="item-box">
            <% if(loginMember != null){ %>
            	<button class="wish-btn" onclick=""><i class="fa-solid fa-heart"></i></button>
            <% }else{ %>
            	<button class="wish-btn" onclick=""><i class="fa-regular fa-heart"></i></button>
            <% } %>
                <a class="item-inner" href="<%=request.getContextPath()%>/product/productId=<%=product.getProductId() %>">
                    <div class="item">
                        <img src="<%=request.getContextPath() %>/images/<%= product.getAttachment().getOriginalFilename() %>"/>
          
                        <div class="info-box">
                            <div class="brand">
                                <p><%= product.getBrand() %></p>
                            </div>
                            <p class="product-title"><%=product.getTitle() %></p>
                            <div class="price">
                                <em class="num"> <%=df.format(product.getPrice()) %>원</em>
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
	<script>
        window.addEventListener('load',()=>{
           //헤더 높이 구하기
           const header = document.querySelector('.header');

           //메인 컨텐츠의 padding top 높이 조절하기
           const frame = document.querySelector('.banner');
           frame.style.paddingTop = `\${header.offsetHeight}px`;
        });
        
        const wishBtnEvent = (e, productId, memberId) =>{
        	if(e.innerHTML === '<i class="fa-regular fa-heart"></i>'){
                //빈 하트이면
                $.ajax({
                    url:"<%=request.getContextPath() %>/wish/addWish",
                    data:{
                    	
                        memberId : memberId,
                        productId : productId
                    },
                    method : "POST",
                    success(response){
                    	//꽉 찬 하트로 변경
                    	console.log("찜하기 성공");
                        e.innerHTML = '<i class="fa-solid fa-heart"></i>';
                    },
                    error:console.log
                });
        	}else{
                //찬 하트이면
                $.ajax({
                    url:"<%=request.getContextPath() %>/wish/delWish",
                    data:{
                        memberId : memberId,
                        productId : productId
                    },
                    method : "POST",
                    success(response){
                    	//빈 하트로 변경
                    	console.log("찜하기 해제");
                        e.innerHTML = '<i class="fa-regular fa-heart"></i>';
                    },
                    error:console.log
                });
            }
        }
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>