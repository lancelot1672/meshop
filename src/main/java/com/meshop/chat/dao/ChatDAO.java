package com.meshop.chat.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.meshop.chat.entity.Message;


public class ChatDAO {
    private Properties properties = new Properties();
	public ChatDAO() {
		//생성됨과 동시에 쿼리 설정 파일 가져오기
		String filename = ChatDAO.class.getResource("/sql/chat-query.properties").getPath();
        try{
            properties.load(new FileReader(filename));

        }catch (IOException e){
            String message = e.getMessage();
        	System.out.println(message);
        }
        System.out.println("filename = " + filename);
	}
	
	public List<Message> findChat(Connection conn, String senderId, String receiverId, int productId) {
		//준비
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> messageList = new ArrayList<>();
        
        // SQL
        //select * from chat where ((sender_id=? and receiver_id=?) or (sender_id=? and receiver_id=?)) and product_id = ?
        String sql = properties.getProperty("findChat");
        
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, senderId);
        	pstmt.setString(2, receiverId);
        	pstmt.setString(3, receiverId);
        	pstmt.setString(4, senderId);
        	pstmt.setInt(5, productId);
        	
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
        		Message m = new Message();
        		m.setNo(rs.getInt("no"));
        		m.setSenderId(rs.getString("sender_id"));
        		m.setReceiverId(rs.getString("receiver_id"));
        		m.setMessage(rs.getString("message"));
        		m.setSendDate(rs.getDate("send_date"));
        		
        		messageList.add(m);
        	}
        }catch(SQLException e) {
        	e.printStackTrace();
        }
		return messageList;
	}
}
