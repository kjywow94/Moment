package com.travelmaker.dto;

import java.time.LocalDateTime;

public class ReviewWithDistance {

	private int id;
	private int uid;
	private LocalDateTime date;
	private String title;
	private String location;
	private double latitude;
	private double longitude;
	private String hash;
	private String hashtag;
	private double distance;
	private int liked;

	public ReviewWithDistance(int id, int uid, LocalDateTime date, String title, String location, double latitude, double longitude,
			String hash, String hashtag, double distance, int liked) {
		super();
		this.id = id;
		this.uid = uid;
		this.date = date;
		this.title = title;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.hash = hash;
		this.hashtag = hashtag;
		this.distance = distance;
		this.liked = liked;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", uid=" + uid + ", date=" + date + ", title=" + title + ", location=" + location
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", hash=" + hash + ", hashtag=" + hashtag
				+ ", distance=" + distance + ", liked=" + liked + "]";
	}

}
