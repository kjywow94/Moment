package com.travelmaker.dto;

import java.time.LocalDateTime;

public class ReviewWithDistanceImg extends ReviewWithDistance {

	private String imageData;

	public ReviewWithDistanceImg(int id, int uid, LocalDateTime date, String title, String location, double latitude,
			double longitude, String hash, String hashtag, double distance, int liked) {
		super(id, uid, date, title, location, latitude, longitude, hash, hashtag, distance, liked);
		// TODO Auto-generated constructor stub
	}

	public ReviewWithDistanceImg(int id, int uid, LocalDateTime date, String title, String location, double latitude,
			double longitude, String hash, String hashtag, double distance, int liked, String imageData) {
		super(id, uid, date, title, location, latitude, longitude, hash, hashtag, distance, liked);
		this.imageData = imageData;
	}

	public ReviewWithDistanceImg(ReviewWithDistance RWD, String imageData) {
		super(RWD.getId(), RWD.getUid(), RWD.getDate(), RWD.getTitle(), RWD.getLocation(), RWD.getLatitude(),
				RWD.getLongitude(), RWD.getHash(), RWD.getHashtag(), RWD.getDistance(), RWD.getLiked());
		this.imageData = imageData;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "ReviewWithDistanceImg [imageData=" + imageData + ", getImageData()=" + getImageData() + ", getId()="
				+ getId() + ", getUid()=" + getUid() + ", getDate()=" + getDate() + ", getTitle()=" + getTitle()
				+ ", getLocation()=" + getLocation() + ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + ", getHash()=" + getHash() + ", getHashtag()=" + getHashtag() + ", getDistance()="
				+ getDistance() + ", getLiked()=" + getLiked() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
