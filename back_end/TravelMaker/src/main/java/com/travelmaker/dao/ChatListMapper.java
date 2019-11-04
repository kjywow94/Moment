package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.Chat;
import com.travelmaker.dto.ChatList;



@Mapper
public interface ChatListMapper {
	public List<ChatList> selectNotiChatListByTarget(int id);
	public List<ChatList> selectNotiChatListByFrom(int id, int uid);
	//로그인
	public List<ChatList> selectChatListByUid(int id);
	public int insertChatList(ChatList cl);
	
	public int updateChatList(ChatList cl);
	
	public int deleteChatList(int id);
	
	public int updateChat(Chat c);
	
}
