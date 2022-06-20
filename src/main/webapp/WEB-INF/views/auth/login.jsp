<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
	String result =(String) request.getAttribute("result");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/login.css">
</head>
<body>
<!-- 로그인폼 시작 -->
 <div id="header">
       <img src="<%=request.getContextPath() %>/resources/images/me_shop_logo.png" id="logo"/>
    </div>


 <div class="login-wrapper">
      <div class="login_content">
       <form id="loginFrm" name="loginFrm" method="POST" action="<%= request.getContextPath() %>/member/login">
        <div>
          <h3 class="login_title">
            <label for="id">아이디</label>
          </h3>
            <input type="text"  name="id" id="id" placeholder="아이디"/>
        </div>
        
        <div>
          <h3 class="login_title"><label for="pswd1">비밀번호</label></h3>
            <input type="password" name="password" id="password" placeholder="비밀번호" />
        </div>

        <div class="login_btn_area">
          <input type="submit" value="로그인">
        </div>
      </form>
        <div class="service">
          <a href="<%= request.getContextPath() %>/meshop/member/join"><span>회원가입</span></a>
          <span>|</span>
          <span>아이디 찾기</span>
          <span>|</span>
           <a href="<%= request.getContextPath() %>/member/find/pw"><span>비밀번호 찾기</span></a>
        </div>
      </div>
    </div>
  </body>
</html>