package com.travelmaker.dto;

public class ReviewImage {

	private String imageData;
	private int imageNum;

	public ReviewImage(String imageData, int imageNum) {
		super();
		this.imageData = imageData;
		this.imageNum = imageNum;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public int getImageNum() {
		return imageNum;
	}

	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

	@Override
	public String toString() {
		return "ReviewImage [imageData=" + imageData + ", imageNum=" + imageNum + "]";
	}

}
