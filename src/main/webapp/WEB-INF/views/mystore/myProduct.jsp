<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>내가 올린 상품</title>
 <div class="header">
        <h2><span class="material-symbols-outlined md-78">location_away</span> 님, 안녕하세요</h2>
    </div>

    <nav class="myhomeContainer">
        <ul>
            <li class="homeItem"><a href="myProduct.html">내가 올린글</a></li>
            <li class="homeItem"><a href="likeProduct.html">찜한 상품</a> </li>
            <li class="homeItem"><a href="resvProcuct.html">예약중인 상품</a></li>
            <li class="homeItem"><a href="completeProcuct.html">거래 완료 상품</a></li>
            <li class="homeItem"><a href="reviewProduct.html">상품 후기</a></li>
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
                <th>상품 상태</th>
                <th> 수정 및 삭제</th>
             </tr>
         </thead>
         <tbody>                  
             <tr>
                <td><img class="my_img" src="../img/grizzly.jpg" alt="사진"></td>
                <td><a href="#">제목 입력창 </a></td>
                <td>
                    <select name="reserv_complete" class="reserv_complete" id="reserv_complete">
                        <option value="" selected hidden>선택</option>
                        <option value="reserv" >예약</option>
                        <option value="sucess">판매 완료</option>
                    </select>        
                </td>
                <td>
                    <button type="button">수정</button>
                    <button type="button">삭제</button>
                </td>
             </tr>
         </tbody>

     </table>
    </div>

    <script>
        function tblMyProduct(prop){
            const imgSrc = prop.imgSrc;
            const subject = prop.subject;

            const elTblMyProduct = document.querySelector('#tblMyProduct');
            const elTbodyMyProduct = elTblMyProduct.querySelector('tbody');
            const elAddTr = document.createElement('tr');
            let _innerHtml = '';
            _innerHtml += `<td><img class="my_img" src="${imgSrc}" alt=""></td>`;
            _innerHtml += `<td><a href="#">${subject}</a></td>`;
            _innerHtml += `<td>`;
            _innerHtml += `    <select name="reserv_complete" class="reserv_complete">`;
            _innerHtml += `        <option value="" selected hidden>선택</option>`;
            _innerHtml += `        <option value="ko" >예약</option>`;
            _innerHtml += `        <option value="ch">판매 완료</option> `;
            _innerHtml += `    </select>`;
            _innerHtml += `</td>`;
            _innerHtml += `<td>`;
            _innerHtml += `    <button type="button">수정</button>`;
            _innerHtml += `    <button type="button">삭제</button>`;
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