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
	private int companionCount;
	private int companionGrade;
	private String password;
	private String sns1;
	private String sns2;
	/**
	 * taste1.구경  : 0 - 번화가 좋아요 / 1 - 자연이 좋아요
	 */
	private int taste1;
	/**
	 * taste2.사진  : 0 - 남는건 사진뿐 / 1 - 눈으로 찍을래
	 */
	private int taste2;
	/**
	 * taste3.음식  : 0 - 맛집 좋아요 / 1 - 아무거나 잘먹어 
	 */
	private int taste3;
	/**
	 * taste4.사람  : 0 - 다양한 사람과 많이 만나기 / 1 - 소수의 사람과 친밀하게
	 */
	private int taste4;
	/**
	 * taste5.계획  : 0 - 계획적인 여행 / 1 - 즉흥적인 여행
	 */
	private int taste5;
	/**
	 * taste6.쇼핑  : 0 - 쇼핑 좋아요 / 1 - 쇼핑 싫어요 
	 */
	private int taste6;
	/**
	 * taste7.경비  : 0 - 경비는 여유롭게 사용 / 1 - 경비는 알뜰살뜰하게 사용
	 */
	private int taste7;
	private String about;
	private Authority authority;
	private String walletAddress;
	private Status isRemove;
	
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
	public int getCompanionCount() {
		return companionCount;
	}
	public void setCompanionCount(int companioncount) {
		this.companionCount = companioncount;
	}
	public int getCompanionGrade() {
		return companionGrade;
	}
	public void setCompanionGrade(int companiongrade) {
		this.companionGrade = companiongrade;
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
	public int getTaste1() {
		return taste1;
	}
	public void setTaste1(int taste1) {
		this.taste1 = taste1;
	}
	public int getTaste2() {
		return taste2;
	}
	public void setTaste2(int taste2) {
		this.taste2 = taste2;
	}
	public int getTaste3() {
		return taste3;
	}
	public void setTaste3(int taste3) {
		this.taste3 = taste3;
	}
	public int getTaste4() {
		return taste4;
	}
	public void setTaste4(int taste4) {
		this.taste4 = taste4;
	}
	public int getTaste5() {
		return taste5;
	}
	public void setTaste5(int taste5) {
		this.taste5 = taste5;
	}
	public int getTaste6() {
		return taste6;
	}
	public void setTaste6(int taste6) {
		this.taste6 = taste6;
	}
	public int getTaste7() {
		return taste7;
	}
	public void setTaste7(int taste7) {
		this.taste7 = taste7;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", birthday=" + birthday + ", gender=" + gender + ", nickname=" + nickname
				+ ", userName=" + userName + ", phone=" + phone + ", email=" + email + ", companioncount="
				+ companionCount + ", companiongrade=" + companionGrade + ", authority=" + authority
				+ ", walletAddress=" + walletAddress +", isRemove=" + isRemove +"]";
	}
}
