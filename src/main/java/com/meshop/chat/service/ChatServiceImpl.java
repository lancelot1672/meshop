package com.meshop.chat.service;

import static com.meshop.common.JdbcTemplate.close;
import static com.meshop.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.meshop.chat.dao.ChatDAO;
import com.meshop.chat.entity.Chatroom;
import com.meshop.chat.entity.Message;

public class ChatServiceImpl {
	ChatDAO chatDAO = new ChatDAO();
	public List<Message> findChat(Message m){
		Connection conn = getConnection();
		List<Message> list = null;
		
		try {
			list = chatDAO.findChat(conn,m);
		}catch(Exception e) {
			
		}finally {
			close(conn);
		}
		return list;
	}
	public List<Chatroom> findChatroomList(String memberId){
		Connection conn = getConnection();
		List<Chatroom> list = null;
		
		try {
			list = chatDAO.findAllChat(conn,memberId);
		}catch(Exception e) {
			
		}finally {
			close(conn);
		}
		return list;
	}
	public int insertChat(Message m) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = chatDAO.insertChat(conn, m);
		}catch(Exception e) {
			
		}finally {
			close(conn);
		}
		return result;
	}
}
