package com.travelmaker.dto;

public class UserImage {
	private String imgName;
	private String imgData;
	private String extension;
	private String email;

	public UserImage(String imgName, String imgData, String extension, String email) {
		super();
		this.imgName = imgName;
		this.imgData = imgData;
		this.extension = extension;
		this.email = email;
	}
	
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgData() {
		return imgData;
	}

	public void setImgData(String imgData) {
		this.imgData = imgData;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserImage [imgName=" + imgName + ", imgData=" + imgData + ", extension=" + extension + ", email="
				+ email + "]";
	}	
}
