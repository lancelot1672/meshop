<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    
    String result = (String)request.getAttribute("result");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Me Shop | 회원가입</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/join.css">
</head>
<body>

 <div id="header">
        <img src="<%=request.getContextPath() %>/resources/images/me_shop_logo.png" id="logo"/>
    </div>

    <div class="join_wrapper">
      <div class="join_content">
        <form id="joinFrm" name="joinFrm" method="post" action="<%= request.getContextPath() %>/member/join">
        <div>
          <h3 class="join_title">
            <label for="id">아이디</label>
          </h3>
            <input type="text" name="id" id="id"  placeholder="내용을 입력해주세요" />
        	<div class="check_btn_area">
        	<button type="button" id="duplCheck" onClick="check()" name="duplCheck">중복체크</button>
       		</div>
        </div>
        <div>
          <h3 class="join_title"><label for="password">비밀번호</label></h3>
            <input type="password" name="password" id="password"  placeholder="내용을 입력해주세요" />
        </div>

        <div>
          <h3 class="join_title"><label for="password2">비밀번호 확인</label></h3>
            <input type="password" name="password2" id="password2"  placeholder="내용을 입력해주세요" />
        </div>
        <div>
          <h3 class="join_title"><label for="name">이름</label></h3>
            <input type="text" id="name" name="name" placeholder="내용을 입력해주세요"/>
        </div>
 		<div>
          <h3 class="join_title"><label for="storeName">상점 이름</label></h3>
            <input type="text" name="storeName" id="storeName"  placeholder="내용을 입력해주세요"/>
            
        </div>        

        <div>
          <h3 class="join_title"><label for="place">내 동네 설정</label></h3>
          <select name="place" id="place">
            <option value="종로구">종로구</option>
            <option value="동대문구">동대문구</option>
            <option value="서대문구">서대문구</option>
            <option value="용산구">용산구</option>
            <option value="중구">중구</option>
          </select>
        </div>

        <div class="join_btn_area">
        <input type="submit" value="가입하기" >
        </div>
      </form>
      </div>
    </div>
    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
    
</body>
</html>
<script>

var result = "<%= result %>";
var isCheckedUsername = false;

function check(){
	
	var id = $('#id').val();
	
	console.log("od :", id);
	
	$.ajax({
		type: 'get',
		url: '/meshop/member/check?id=' + id
	}).done(function(result){
   	 	console.log(result);
   	 	if(result==1){
			alert("아이디가 중복되었습니다.");
   	 	}else if(result==0){
			alert("사용하실 수 있는 아이디입니다.");
			isCheckedUsername=true;		
   	   	}else{
			console.log('develop : 서버 오류');
   	   	}
 	}).fail(function(error){
   	 	console.log(error);

 	});
}

// null check
document.joinFrm.onsubmit = (e) => {
	const frm = e.target;
	// 아이디
	if(!/^.+$/.test(frm.id.value.trim())) {
		alert("아이디를 입력해주세요.");
		frm.id.select();
		return false;
	}
	
	// 패스워드
	if(!/^.+$/.test(frm.password.value.trim())) {
		alert("패스워드를 입력해주세요.");
		frm.password.select();
		return false;
	}

	// 패스워드 확인
	if(!/^.+$/.test(frm.password2.value.trim())) {
		alert("패스워드 확인을 입력해주세요.");
		frm.password2.select();
		return false;
	}

	// 이름 
	if(!(frm.name.value)) {
		alert("이름을 입력해주세요.");
		frm.name.select();
		return false;
	}
	
	// 상점 이름 
	if(!(frm.storeName.value)) {
		alert("상점이름을 입력해주세요.");
		frm.storeName.select();
		return false;
	}
	
	if(!(frm.password2.value == frm.password.value)) {
		alert("비밀번호 값이 일치하지 않습니다.");
		frm.password.select();
		return false;
	}
	
	
}

</script>