<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/chatroom.css">
<div class="frame">
    <div class="chatroom-section">
        <div class="chatroom">
            <a href="#">
            <div class="room-content">
                <span class="chat-store-name">동동목장</span>
                <span class="chat-title">타이들타이틀</span>
            </div>
            </a>
        </div>
        <div class="chatroom">
            <a href="#">
            <div class="room-content">
                <span class="chat-store-name">동동목장</span>
                <span class="chat-title">타이들타이틀</span>
            </div>
            </a>
        </div>
    </div>
    <div class="chat-section">
       <div class="chatroom-info">
           <h2>TitleTitleTitleTitleTitle</h2>
           <span>동동목장</span>
       </div>
       <div class="chat-container">
           <ul>
           </ul>
       </div>
       <div class="input-section">
           <textarea id="chat_area"></textarea>
           <button id="chat_btn">전송</button>
       </div>
   </div>
</div>
<script>
    window.addEventListener('load',()=>{
        //헤더 높이 구하기
        const header = document.querySelector('.header');

        //메인 컨텐츠의 padding top 높이 조절하기
        const frame = document.querySelector('.frame');
        frame.style.paddingTop = `\${header.offsetHeight}px`;
        
        let senderId = 'abcd';
        let receiverId = 'qwer';
        let productId = 1;
        $.ajax({
            url : "<%=request.getContextPath() %>/chat/content",
            method : "POST",
            data : {
                senderId : senderId,
                receiverId : receiverId,
                productId : productId
            },
            success(response){
                console.log(response);
                const ul = document.querySelector('.chat-container ul');
                ul.innerHTML = "";
                response.forEach((chat)=>{
                    // 구조분해 할당
                    const {no,senderId,receiverId, message, sendDate} = chat;
                    let html = "";
                    if(senderId === 'abcd'){
                        html = `<li class="me"><p class="message">\${message}</p></li>`;
                    }else{
                        html = `<li class="you"><p class="who">김동동 : </p><p class="message">\${message}</p></li>`;
                    }
                    ul.insertAdjacentHTML('beforeend',html);
                });
            },
            error: console.log
        });
    });
    chat_btn.addEventListener('click',()=>{
        //textarea 값 가져오기
        const value = document.querySelector('#chat_area').value;
        console.log(value);

        const ul = document.querySelector('.chat-container ul');
        let html = `<li class="me"><p>$\{value}</p></li>`;
        ul.insertAdjacentHTML('beforeend',html);
        document.querySelector('#chat_area').value = "";
    });
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>