package com.travelmaker.dto;

import java.time.LocalDateTime;

import com.travelmaker.dto.enumeration.Status;

public class UserReview {

	private int id;
	private int uid;
	private String review;
	private int grade;
	private int tid;
	private Status isRemove;
	private LocalDateTime endDate;

	public UserReview() {
		super();
	}

	public UserReview(int id, int uid, String review, int grade, int tid, Status status) {
		super();
		this.id = id;
		this.uid = uid;
		this.review = review;
		this.grade = grade;
		this.tid = tid;
		this.isRemove = status;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public Status getIsRemove() { 
		return isRemove;
	}
	
	public void setIsRemove(Status isRemove) {
		this.isRemove = isRemove;
	}
	
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "UserReview [id=" + id + ", uid=" + uid + ", review=" + review + ", grade=" + grade + ", tid=" + tid
				+ ", isRemove=" + isRemove + "]";

	}

}
