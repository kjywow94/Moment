package com.travelmaker.dto;

import java.time.LocalDateTime;

import com.travelmaker.dto.enumeration.Status;
import com.travelmaker.dto.enumeration.Type;

public class Chat {
	private int id;
	private int uid;
	private String context;
	private LocalDateTime timestamp;
	private int roomNumber;
	private Status isRemove;
	private int target;
	private Type type;
	public Chat() {
		super();
	}
	
	public Chat(int id, int uid, String context, LocalDateTime timestamp, int roomNumber, Status isRemove, int target, Type type) {
		super();
		this.id = id;
		this.uid = uid;
		this.context = context;
		this.timestamp = timestamp;
		this.roomNumber = roomNumber;
		this.isRemove = isRemove;
		this.target = target;
		this.type = type;
	}

	

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
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
				+ roomNumber + ", isRemove=" + isRemove + ", target=" + target + ", type=" + type + "]";
	}
	
	
	
}
