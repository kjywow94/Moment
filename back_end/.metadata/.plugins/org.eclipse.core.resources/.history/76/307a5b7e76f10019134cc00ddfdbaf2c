package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.ChatMapper;
import com.travelmaker.dto.Chat;
import com.travelmaker.dto.UserReview;

@Service
public class ChatService {

	@Autowired
	private ChatMapper chatMapper;

	public List<Chat> selectAll(int rn) {
		return chatMapper.selectAll(rn);
	}

	public List<Chat> selectAllNotRemovedReview(int rn) {
		return chatMapper.selectAllNotRemovedReview(rn);
	}

	public int insertChat(Chat c) {
		return chatMapper.insertChat(c);
	}

	public int deleteChatById(int id) {
		return chatMapper.deleteChatById(id);
	};

	public int updateChat(Chat c) {
		return chatMapper.updateChat(c);
	}

}
