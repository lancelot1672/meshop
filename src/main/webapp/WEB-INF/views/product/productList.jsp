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
				%>
    			<div class="productBox">
        			<a class="productLink" href="">
	           			<div class="productImage" style="background-image: url('<%= request.getContextPath() %>/images/<%= list.get(j).getAttachment().getRenamedFilename() %>'); background-size: cover;">           
							<input type="checkbox" name="wishBtn" id="wishBtn1">
	    					<label for="wishBtn1">♡</label>
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

document.querySelectorAll('input[name=wishBtn]').forEach((input) => {
    input.addEventListener('click', (e) => {
        if(e.target.checked) {
            e.target.nextElementSibling.innerHTML = "♥";
        } else {
            e.target.nextElementSibling.innerHTML = "♡"
        }
    });      
});

/* document.querySelectorAll('.date').forEach((date) => {
	new Date()
}); */

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
