package com.travelmaker.dto;

public class Location {

	private double latitude;
	private double longitude;
	private int distance;
	private int start;
	private int end;

	public Location(double latitude, double longitude, int distance, int start, int end) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
		this.start = start;
		this.end = end;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
