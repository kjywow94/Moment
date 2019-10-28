package com.travelmaker.dto;

import java.time.LocalDateTime;

import com.travelmaker.dto.enumeration.Status;

public class Chat {
	private int id;
	private int uid;
	private String context;
	private LocalDateTime timestamp;
	private int roomNumber;
	private Status isRemove;
	public Chat() {
		super();
	}
	public Chat(int iD, int uID, String context, LocalDateTime timestamp, int room_number, Status is_remove) {
		super();
		id = iD;
		uid = uID;
		this.context = context;
		this.timestamp = timestamp;
		this.roomNumber = room_number;
		this.isRemove = is_remove;
	}
	public int getId() {
		return id;
	}
	public void setId(int iD) {
		id = iD;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uID) {
		uid = uID;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int room_number) {
		this.roomNumber = room_number;
	}
	public Status getIsRemove() {
		return isRemove;
	}
	public void setIsRemove(Status is_remove) {
		this.isRemove = is_remove;
	}
	@Override
	public String toString() {
		return "Chat [id=" + id + ", uid=" + uid + ", context=" + context + ", timestamp=" + timestamp + ", roomNumber="
				+ roomNumber + ", isRemove=" + isRemove + "]";
	}
	
	
	
}
