package com.travelMaker.dto;

import java.time.LocalDateTime;

import com.travelMaker.dto.enumeration.Status;

public class AccompanyParti {
	private int id;
	private int tid;
	private int uid;
	private LocalDateTime participateTime;
	private LocalDateTime realStartDDate;
	private LocalDateTime realEndDate;
	private Status status;

	public AccompanyParti() {
		super();
	}

	public AccompanyParti(int id, int tid, int uid, LocalDateTime participateTime, LocalDateTime realStartDDate,
			LocalDateTime realEndDate, Status status) {
		super();
		this.id = id;
		this.tid = tid;
		this.uid = uid;
		this.participateTime = participateTime;
		this.realStartDDate = realStartDDate;
		this.realEndDate = realEndDate;
		this.status = status;
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

	public LocalDateTime getRealStartDDate() {
		return realStartDDate;
	}

	public void setRealStartDDate(LocalDateTime realStartDDate) {
		this.realStartDDate = realStartDDate;
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
				+ ", realStartDDate=" + realStartDDate + ", realEndDate=" + realEndDate + ", status=" + status + "]";
	}

}
