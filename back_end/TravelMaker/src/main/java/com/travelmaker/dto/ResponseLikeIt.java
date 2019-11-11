package com.travelmaker.dto;

public class ResponseLikeIt {

	private int max;
	private int id;
	private boolean isPoint;

	public ResponseLikeIt(int max, int id, boolean isPoint) {
		super();
		this.max = max;
		this.id = id;
		this.isPoint = isPoint;
	}

	public boolean isPoint() {
		return isPoint;
	}

	public void setPoint(boolean isPoint) {
		this.isPoint = isPoint;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ResponseLikeIt [max=" + max + ", id=" + id + "]";
	}
}
