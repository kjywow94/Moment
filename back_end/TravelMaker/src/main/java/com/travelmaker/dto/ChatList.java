package com.travelmaker.dto;
public class ChatList {
	private int id;
	private int uid;
	private int target;
	private int notification;
	private String nickname;
	
	
	
	public ChatList() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ChatList(int id, int uid, int target, int notification, String nickname) {
		super();
		this.id = id;
		this.uid = uid;
		this.target = target;
		this.notification = notification;
		this.nickname = nickname;
	}
	
	
	
	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getNotification() {
		return notification;
	}
	public void setNotification(int notification) {
		this.notification = notification;
	}
	@Override
	public String toString() {
		return "ChatList [id=" + id + ", uid=" + uid + ", target=" + target + ", notification=" + notification
				+ ", nickname=" + nickname + "]";
	}
	
	
	
}
