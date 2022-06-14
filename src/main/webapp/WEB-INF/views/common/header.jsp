<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <title>#MESHOP</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
  <script src="https://kit.fontawesome.com/69223d03fa.js" crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="header">
        <div class="auth-section">
            <ul class="nav-auth">
                <li><a class="auth-menu" href="./login.html">로그인</a></li>
                <li><a class="auth-menu" href="./joinForm.html">회원가입</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="logo">
                <a href="#"><img id="logo-img" src="<%= request.getContextPath() %>/images/logo.png" alt="메인 페이지"></a>
            </div>
            <div class="search-section">
                <div class="search-bar">
                    <input type="text" placeholder="검색어를 입력하세요"/>
                    <button><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            <div class="menu">
                <ul class="nav-menu">
                    <li><a class="" href="#">글쓰기</a></li>
                    <li><a class="" href="#">채팅방</a></li>
                    <li><a class="" href="#">내 상점</a></li>
                </ul>
            </div>
        </div>
    </div>
