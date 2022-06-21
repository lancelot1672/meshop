<%@page import="com.meshop.product.entity.ProductStatus"%>
<%@page import="com.meshop.product.entity.ProductExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	List<ProductExt> list = (List<ProductExt>) request.getAttribute("productList");
	String pagebar = (String) request.getAttribute("pagebar");
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
                </select>
            </div>
        </section>
        <section id="listSection-list">
       	<% 
       		if(list != null && !list.isEmpty()) {
       			int index = 0;
        		for(int i = 0; i < (int) Math.ceil((double) list.size() / 4); i++) {
       	%>
			<div class="productSection">
				<% 
					for(int j = 0; j < 4; j++) {
				%>
    			<div class="productBox">
					<% if(loginMember != null && wishList.contains(list.get(index).getProductId())){ %>
		            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=list.get(index).getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-solid fa-heart"></i></button>
		            <% }else if(loginMember != null){ %>
		            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=list.get(index).getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-regular fa-heart"></i></button>
		            <% }else{ %>
		            	<button class="wish-btn" onclick="location.href='<%=request.getContextPath() %>/login';"><i class="fa-regular fa-heart"></i></button>
		            <% } %>
        			<a class="productLink" href="<%= request.getContextPath() %>/product/productView?productId=<%= list.get(index).getProductId() %>">
	           			<div class="productImage" style="background-image: url('<%= request.getContextPath() %>/images/<%= list.get(index).getAttachment().getRenamedFilename() %>'); background-size: cover;">           

						</div>
						<div class="productInfo">
	    					<div class="list-brand"><%= list.get(index).getBrand() %></div>
							<div class="list-title"><%= list.get(index).getStatus() == ProductStatus.N ? "[새상품]" : "" %> <%= list.get(index).getTitle() %></div>
							<div class="list-price"><%= list.get(index).getPrice() %>원</div>
							<div class="list-other">
	    						<span class="list-place">서울시 <%= list.get(index).getPlace() %></span>
								<span class="list-date"><%= list.get(index).getRegDate() %></span>
	             			</div>
	         			</div>
     				</a>
				</div>
				<%
						index++;
						if(index == list.size()) break;
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
// 새 상품, 내 동네 toggle
document.querySelectorAll('#listSection-toggle input').forEach((input) => {
	input.addEventListener('change', (e) => {
	<%
		if(loginMember != null) {
	%>
		$.ajax({
			url:"<%=request.getContextPath() %>/product/productSortToggle",
            data:{
            	place : "<%= loginMember.getPlace() %>",
                statusToggle : statusToggle.checked,
                placeToggle : placeToggle.checked
            },
            method : "GET",
            success(response){
            	for(let i = 0; i < response.length; i++) {
            		
            	}
            },
            error:console.log
		})
	<% } else { %>
		alert('로그인이 필요합니다.');
		e.target.checked = '';
	<%	} %>
	});

});

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
};

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
