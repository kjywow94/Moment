package com.travelmaker.dto;
import java.time.LocalDateTime;

import com.travelmaker.dto.enumeration.Process;
import com.travelmaker.dto.enumeration.Region;
import com.travelmaker.dto.enumeration.Status;

public class AccompanyRegist {
	private int id;
	private String title;
	private String content;
	private Process process;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String city;
	private Region region;
	private String thema;
	private int view;
	private int max;
	private String hashtag;
	private Status isRemove;
	
	public Status getIsRemove() {
		return isRemove;
	}
	public void setIsRemove(Status isRemove) {
		this.isRemove = isRemove;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public String getThema() {
		return thema;
	}
	public void setThema(String thema) {
		this.thema = thema;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public AccompanyRegist(int id, String title, String content, Process process, LocalDateTime startDate,
			LocalDateTime endDate, String city, Region region, String thema, int view, int max, String hashtag,
			Status isRemove) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.process = process;
		this.startDate = startDate;
		this.endDate = endDate;
		this.city = city;
		this.region = region;
		this.thema = thema;
		this.view = view;
		this.max = max;
		this.hashtag = hashtag;
		this.isRemove = isRemove;
	}
	@Override
	public String toString() {
		return "AccompanyRegist [id=" + id + ", title=" + title + ", content=" + content + ", process=" + process
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", city=" + city + ", region=" + region
				+ ", thema=" + thema + ", view=" + view + ", max=" + max + ", hashtag=" + hashtag + ", isRemove="
				+ isRemove + ", getIsRemove()=" + getIsRemove() + ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getContent()=" + getContent() + ", getProcess()=" + getProcess() + ", getStartDate()="
				+ getStartDate() + ", getEndDate()=" + getEndDate() + ", getCity()=" + getCity() + ", getRegion()="
				+ getRegion() + ", getThema()=" + getThema() + ", getView()=" + getView() + ", getMax()=" + getMax()
				+ ", getHashtag()=" + getHashtag() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


}
