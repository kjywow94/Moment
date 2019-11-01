package com.travelmaker.dto;

public class ReviewPoint {
	private int id;
	private int rid;
	private int point;

	public ReviewPoint(int id, int rid, int point) {
		super();
		this.id = id;
		this.rid = rid;
		this.point = point;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
