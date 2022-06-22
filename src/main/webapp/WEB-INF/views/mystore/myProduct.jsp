<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	

%>
<title>내가 올린 상품</title>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/mystore.css">
 <div class="header">
        <h2><span class="material-symbols-outlined md-78">location_away</span> 님, 안녕하세요</h2>
    </div>

    <nav class="myhomeContainer">
        <ul>
            <li class="homeItem"><a href="myProduct.jsp">내가 올린글</a></li>
            <li class="homeItem"><a href="likeProduct.jsp">찜한 상품</a> </li>
            <li class="homeItem"><a href="resvProcuct.jsp">예약중인 상품</a></li>
            <li class="homeItem"><a href="completeProcuct.jsp">거래 완료 상품</a></li>
            <li class="homeItem"><a href="reviewProduct.jsp">상품 후기</a></li>
        </ul> 
    </nav>

    <div class="board_list_wrap">
     <table class="board_list" id="tblMyProduct">
         <caption>내가 올린 상품 목록</caption>
         <!-- 테이블에서 열의 너비를 정해주는 태그 -->
         <colgroup>
            <col style="width: 25%">
            <col style="width: 25%">
            <col style="width: 25%">
            <col style="width: 25%">
        </colgroup>
         <thead>
             <tr>
                <th>사진</th>
                <th>제목</th>
                <th>내용</th>
              
             </tr>
         </thead>
         <tbody>                  
             <tr>
                <td><img class="my_img" src="../img/grizzly.jpg" alt="사진"></td>
                <td><a href="#"><%= product.getStatus() == ProductStatus.N ? "[새상품] " : "" %><%=product.getTitle() %> </a></td>
                <td><a href="#"></a><%= product.getContent() %></td>
             </tr>
         </tbody>

     </table>
    </div>

    <script>
        function tblMyProduct(prop){
            const imgSrc = prop.imgSrc;
            const subject = prop.subject;
            const

            const elTblMyProduct = document.querySelector('#tblMyProduct');
            const elTbodyMyProduct = elTblMyProduct.querySelector('tbody');
            const elAddTr = document.createElement('tr');
            let _innerHtml = '';
            _innerHtml += `<td><img class="my_img" src="${imgSrc}" alt=""></td>`;
            _innerHtml += `<td><a href="#">${subject}</a></td>`;
            _innerHtml += `<td>`;
            _innerHtml += `<td><a href="#"></a><%= product.getContent() %></td>`;

            _innerHtml += `</td>`;
      
        
            elAddTr.innerHTML = _innerHtml;
            elTbodyMyProduct.append(elAddTr);
        }
        
        document.addEventListener('DOMContentLoaded', ()=>{

            tblMyProduct({
                imgSrc: '../img/grizzly.jpg',
                subject: '곰돌잉'
            });
        });

    </script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>