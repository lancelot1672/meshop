<%@page import="com.meshop.product.entity.ProductStatus"%>
<%@page import="com.meshop.product.entity.ProductExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	List<ProductExt> list = (List<ProductExt>) request.getAttribute("productList");
	String pagebar = (String) request.getAttribute("pagebar");
	int numPerPage = (int) request.getAttribute("numPerPage");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/productList.css">
<main>
    <section id="categorySection">
        <div id="categoryToggle">
            <span>카테고리</span>
            <span id="add">+</span>
        </div>
        <span class="categoryList">상의</span>
        <span class="categoryList">하의</span>
        <span class="categoryList">신발</span>
        <span class="categoryList">잡화</span>
        <span class="categoryList">럭셔리</span>
    </section>
    <section id="listSection">
        <section id="listSection-toggle">
            <!-- 새상품, 내동네 toggle -->
            <div>
                <input type="checkbox" name="statusToggle" id="statusToggle">
                <label for="statusToggle">
                    <div class="toggleBtn">
                        <span class="onoff"></span>
                    </div>
                    <span class="content">새 상품</span>
                </label>
                <input type="checkbox" name="placeToggle" id="placeToggle">
                <label for="placeToggle">
                    <div class="toggleBtn">
                        <span class="onoff"></span>
                    </div>
                    <span class="content">내 동네</span>
                </label>
            </div>
            <!-- 조회조건 리스트 -->
            <div>
                <select name="viewCondition" id="viewCondition">
                    <option value="orderByDay" selected>최신순&nbsp;&nbsp;</option>
                    <option value="orderByPrice">가격순</option>
                    <option value="orderByView">조회순</option>
                </select>
            </div>
        </section>
        <section id="listSection-list">
       	<% 
       		if(list != null && !list.isEmpty()) {
        		for(int i = 0; i < (int) Math.ceil((double) list.size() / 4); i++) {
       	%>
			<div class="productSection">
				<% 
					for(int j = 0; j < 4; j++) {
			        	ProductExt product = list.get(i);
				%>
    			<div class="productBox">
        			<a class="productLink" href="">
	           			<div class="productImage" style="background-image: url('<%= request.getContextPath() %>/images/<%= list.get(j).getAttachment().getRenamedFilename() %>'); background-size: cover;">           
				            <% if(loginMember != null && wishList.contains(product.getProductId())){ %>
				            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=product.getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-solid fa-heart"></i></button>
				            <% }else if(loginMember != null){ %>
				            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=product.getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-regular fa-heart"></i></button>
				            <% }else{ %>
				            	<button class="wish-btn" onclick="location.href='<%=request.getContextPath() %>/member/login';"><i class="fa-regular fa-heart"></i></button>
				            <% } %>
						</div>
						<div class="productInfo">
	    					<div class="brand"><%= list.get(i).getBrand() %></div>
							<div class="title"><%= list.get(i).getStatus() == ProductStatus.N ? "[새상품]" : "" %> <%= list.get(i).getTitle() %></div>
							<div class="price"><%= list.get(i).getPrice() %>원</div>
							<div class="other">
	    						<span class="place">서울시 <%= list.get(i).getPlace() %></span>
								<span class="date"><%= list.get(i).getRegDate() %></span>
	             			</div>
	         			</div>
     				</a>
				</div>
				<%
					}
				%>
			</div>
		<% 
				}
			} 
		%>
        </section>
            <div id="pagebar">
            	<%= pagebar %>
            </div>
        </section>
    </section>
</main>
<script>
document.querySelectorAll('#categoryToggle span').forEach((span) => {
    span.addEventListener('click', () => {
        if(categorySection.classList.contains('on')) {
            categorySection.classList.remove('on');
            add.innerHTML = "+";
        } else {
            categorySection.classList.add('on');
            add.innerHTML = "-";
        }
    })
});

document.querySelectorAll('.categoryList').forEach((span) => {
    span.addEventListener('click' , (e) => {
        document.querySelectorAll('.categoryList').forEach((sp) => {
            sp.style.fontWeight = "normal";
        })
        e.target.style.fontWeight = "bold";
    });
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

/* document.querySelectorAll('.date').forEach((date) => {
	new Date()
}); */

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
