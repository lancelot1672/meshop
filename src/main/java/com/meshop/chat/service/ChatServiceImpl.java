package com.meshop.chat.service;

import static com.meshop.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.meshop.chat.dao.ChatDAO;
import com.meshop.chat.entity.Message;

public class ChatServiceImpl {
	ChatDAO chatDAO = new ChatDAO();
	public List<Message> findChat(String senderId, String receiverId, int productId){
		Connection conn = getConnection();
		return chatDAO.findChat(conn,senderId,receiverId,productId);
	}
}
