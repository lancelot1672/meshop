<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- �α����� ���� -->
<form id="loginFrm" name="loginFrm" method="POST" action="<%= request.getContextPath() %>/login">
	<table>
		<tr>
			<td><input type="text" name="memberId" id="memberId" placeholder="���̵�" tabindex="1"></td>
			<td><input type="submit" value="�α���" tabindex="3"></td>
		</tr>
		<tr>
			<td><input type="password" name="password" id="password" placeholder="��й�ȣ" tabindex="2"></td>
			<td></td>
		</tr>
	</table>
</form>
</body>
</html>