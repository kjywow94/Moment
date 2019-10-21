package com.travelmaker.dto;

import java.time.LocalDateTime;

import com.travelmaker.dto.enumeration.Status;

public class AccompanyParti {
	private int id;
	private int tid;
	private int uid;
	private LocalDateTime participateTime;
	private LocalDateTime realStartDate;
	private LocalDateTime realEndDate;
	private Status status;

	public AccompanyParti() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public LocalDateTime getParticipateTime() {
		return participateTime;
	}

	public void setParticipateTime(LocalDateTime participateTime) {
		this.participateTime = participateTime;
	}

	public LocalDateTime getRealStartDate() {
		return realStartDate;
	}

	public void setRealStartDate(LocalDateTime realStartDate) {
		this.realStartDate = realStartDate;
	}

	public LocalDateTime getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(LocalDateTime realEndDate) {
		this.realEndDate = realEndDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccompanyParti [id=" + id + ", tid=" + tid + ", uid=" + uid + ", participateTime=" + participateTime
				+ ", realStartDate=" + realStartDate + ", realEndDate=" + realEndDate + ", status=" + status + "]";
	}

	public AccompanyParti(int id, int tid, int uid, LocalDateTime participateTime, LocalDateTime realStartDate,
			LocalDateTime realEndDate, Status status) {
		super();
		this.id = id;
		this.tid = tid;
		this.uid = uid;
		this.participateTime = participateTime;
		this.realStartDate = realStartDate;
		this.realEndDate = realEndDate;
		this.status = status;
	}

	

}
