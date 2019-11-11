package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.ChatListMapper;
import com.travelmaker.dto.Chat;
import com.travelmaker.dto.ChatList;

@Service
public class ChatListService {

	@Autowired
	private ChatListMapper chatListMapper;

	public List<ChatList> selectNotiChatListByTarget(int id) {
		return chatListMapper.selectNotiChatListByTarget(id);
	}
	public List<ChatList> selectNotiChatListByFrom(int id, int uid) {
		return chatListMapper.selectNotiChatListByFrom(id, uid);
	}
	public int selectAllNotiByUid(int uid) {
		return chatListMapper.selectAllNotiByUid(uid);
	}
	public List<ChatList> selectChatListByUid(int id) {
		return chatListMapper.selectChatListByUid(id);
	}

	public int insertChatList(ChatList cl) {
		return chatListMapper.insertChatList(cl);
	}

	public int deleteChatList(int id) {
		return chatListMapper.deleteChatList(id);
	};

	public int updateChatList(ChatList cl) {
		return chatListMapper.updateChatList(cl);
	}
	

}
