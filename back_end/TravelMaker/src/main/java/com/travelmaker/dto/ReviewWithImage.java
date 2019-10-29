package com.travelmaker.dto;

import java.time.LocalDateTime;

public class ReviewWithImage extends Review {

	private String imageData;

	public ReviewWithImage(int id, int uid, LocalDateTime date, String title, String location, double latitude,
			double longitude, String hash, String hashtag, int liked) {
		super(id, uid, date, title, location, latitude, longitude, hash, hashtag, liked);
		// TODO Auto-generated constructor stub
	}

	public ReviewWithImage(Review review, String imageData) {
		super(review.getId(), review.getUid(), review.getDate(), review.getTitle(), review.getLocation(),
				review.getLatitude(), review.getLongitude(), review.getHash(), review.getHashtag(), review.getLiked());
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
		return "ReviewWithImage [imageData=" + imageData + ", getImageData()=" + getImageData() + ", getId()=" + getId()
				+ ", getUid()=" + getUid() + ", getDate()=" + getDate() + ", getTitle()=" + getTitle()
				+ ", getLocation()=" + getLocation() + ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + ", getHash()=" + getHash() + ", getHashtag()=" + getHashtag() + ", getLiked()="
				+ getLiked() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
