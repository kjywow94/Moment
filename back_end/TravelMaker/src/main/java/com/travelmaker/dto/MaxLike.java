package com.travelmaker.dto;

public class MaxLike {
	private int id;
	private int uid;
	private int rid;
	private int max;

	public MaxLike(int id, int uid, int rid, int max) {
		super();
		this.id = id;
		this.uid = uid;
		this.rid = rid;
		this.max = max;
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

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "MaxLike [id=" + id + ", uid=" + uid + ", rid=" + rid + ", max=" + max + "]";
	}
}
