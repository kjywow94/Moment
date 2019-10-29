package com.travelmaker.dto;

import java.time.LocalDateTime;

import com.travelmaker.dto.enumeration.Authority;
import com.travelmaker.dto.enumeration.Gender;
import com.travelmaker.dto.enumeration.Status;

public class User {
	private int id;
	private LocalDateTime birthday;
	private Gender gender;
	private String nickname;
	private String userName;
	private String phone;
	private String email;
	private String password;
	private String sns1;
	private String sns2;
	private String about;
	private Authority authority;
	private String walletAddress;
	private Status isRemove;
	private String cert;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSns1() {
		return sns1;
	}
	public void setSns1(String sns1) {
		this.sns1 = sns1;
	}
	public String getSns2() {
		return sns2;
	}
	public void setSns2(String sns2) {
		this.sns2 = sns2;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	public String getWalletAddress() {
		return walletAddress;
	}
	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}
	public Status getIsRemove() { 
		return isRemove;
	}
	public void setIsRemove(Status isRemove) {
		this.isRemove = isRemove;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", birthday=" + birthday + ", gender=" + gender + ", nickname=" + nickname
				+ ", userName=" + userName + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ ", sns1=" + sns1 + ", sns2=" + sns2 + ", about=" + about + ", authority=" + authority
				+ ", walletAddress=" + walletAddress + ", isRemove=" + isRemove + "]";
	}
}
