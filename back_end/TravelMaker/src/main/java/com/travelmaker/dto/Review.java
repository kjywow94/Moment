package com.travelmaker.dto;

import java.time.LocalDateTime;

public class Review {

	private int id;
	private int uid;
	private LocalDateTime date;
	private String titie;
	private String location;
	private double latitude;
	private double longitude;
	private String hash;
	private String hashtag;
	private String distance;

	public Review(int id, int uid, LocalDateTime date, String titie, String location, double latitude, double longitude,
			String hash, String hashtag, String distance) {
		super();
		this.id = id;
		this.uid = uid;
		this.date = date;
		this.titie = titie;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.hash = hash;
		this.hashtag = hashtag;
		this.distance = distance;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getTitie() {
		return titie;
	}

	public void setTitie(String titie) {
		this.titie = titie;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", uid=" + uid + ", date=" + date + ", titie=" + titie + ", location=" + location
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", hash=" + hash + ", hashtag=" + hashtag
				+ ", distance=" + distance + "]";
	}
}
