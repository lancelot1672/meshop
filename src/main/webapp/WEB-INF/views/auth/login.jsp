<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- 로그인폼 시작 -->
<form id="loginFrm" name="loginFrm" method="POST" action="<%= request.getContextPath() %>/login">
	<table>
		<tr>
			<td><input type="text" name="memberId" id="memberId" placeholder="아이디" tabindex="1"></td>
			<td><input type="submit" value="로그인" tabindex="3"></td>
		</tr>
		<tr>
			<td><input type="password" name="password" id="password" placeholder="비밀번호" tabindex="2"></td>
			<td></td>
		</tr>
	</table>
</form>
</body>
</html>