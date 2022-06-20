<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    
    String result = (String)request.getAttribute("result");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Me Shop | ȸ������</title>
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
            <label for="id">���̵�</label>
          </h3>
            <input type="text" name="id" id="id"  placeholder="������ �Է����ּ���" />
        	<div class="check_btn_area">
        	<button type="button" id="duplCheck" onClick="check()" name="duplCheck">�ߺ�üũ</button>
       		</div>
        </div>
        <div>
          <h3 class="join_title"><label for="password">��й�ȣ</label></h3>
            <input type="password" name="password" id="password"  placeholder="������ �Է����ּ���" />
        </div>

        <div>
          <h3 class="join_title"><label for="password2">��й�ȣ Ȯ��</label></h3>
            <input type="password" name="password2" id="password2"  placeholder="������ �Է����ּ���" />
        </div>
        <div>
          <h3 class="join_title"><label for="name">�̸�</label></h3>
            <input type="text" id="name" name="name" placeholder="������ �Է����ּ���"/>
        </div>
 		<div>
          <h3 class="join_title"><label for="storeName">���� �̸�</label></h3>
            <input type="text" name="storeName" id="storeName"  placeholder="������ �Է����ּ���"/>
            
        </div>        

        <div>
          <h3 class="join_title"><label for="place">�� ���� ����</label></h3>
          <select name="place" id="place">
            <option value="���α�">���α�</option>
            <option value="���빮��">���빮��</option>
            <option value="���빮��">���빮��</option>
            <option value="��걸">��걸</option>
            <option value="�߱�">�߱�</option>
          </select>
        </div>

        <div class="join_btn_area">
        <input type="submit" value="�����ϱ�" >
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
			alert("���̵� �ߺ��Ǿ����ϴ�.");
   	 	}else if(result==0){
			alert("����Ͻ� �� �ִ� ���̵��Դϴ�.");
			isCheckedUsername=true;		
   	   	}else{
			console.log('develop : ���� ����');
   	   	}
 	}).fail(function(error){
   	 	console.log(error);

 	});
}

// null check
document.joinFrm.onsubmit = (e) => {
	const frm = e.target;
	// ���̵�
	if(!/^.+$/.test(frm.id.value.trim())) {
		alert("���̵� �Է����ּ���.");
		frm.id.select();
		return false;
	}
	
	// �н�����
	if(!/^.+$/.test(frm.password.value.trim())) {
		alert("�н����带 �Է����ּ���.");
		frm.password.select();
		return false;
	}

	// �н����� Ȯ��
	if(!/^.+$/.test(frm.password2.value.trim())) {
		alert("�н����� Ȯ���� �Է����ּ���.");
		frm.password2.select();
		return false;
	}

	// �̸� 
	if(!(frm.name.value)) {
		alert("�̸��� �Է����ּ���.");
		frm.name.select();
		return false;
	}
	
	// ���� �̸� 
	if(!(frm.storeName.value)) {
		alert("�����̸��� �Է����ּ���.");
		frm.storeName.select();
		return false;
	}
	
	if(!(frm.password2.value == frm.password.value)) {
		alert("��й�ȣ ���� ��ġ���� �ʽ��ϴ�.");
		frm.password.select();
		return false;
	}
	
	
}

</script>