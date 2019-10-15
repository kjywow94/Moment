package com.travelMaker.dto;

public class TravelReview {

	private int id;
	private int tid;
	private String title;
	private String hashtag;
	private String review;
	
	
	public TravelReview() {
		super();
	}
	public TravelReview(int id, int tid, String title, String hashtag, String review) {
		super();
		this.id = id;
		this.tid = tid;
		this.title = title;
		this.hashtag = hashtag;
		this.review = review;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	@Override
	public String toString() {
		return "Travel [id=" + id + ", tid=" + tid + ", title=" + title + ", hashtag=" + hashtag + ", review=" + review
				+ "]";
	}
	
	
	
}
