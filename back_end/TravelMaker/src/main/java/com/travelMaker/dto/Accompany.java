package com.travelMaker.dto;

public class Accompany {

	private int id;
	private int cid;
	private int isEnd;
	private String contractAddress;

	public Accompany() {
		super();
	}

	public Accompany(int id, int cid, int isEnd, String contractAddress) {
		super();
		this.id = id;
		this.cid = cid;
		this.isEnd = isEnd;
		this.contractAddress = contractAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getIs_end() {
		return isEnd;
	}

	public void setIs_end(int isEnd) {
		this.isEnd = isEnd;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	@Override
	public String toString() {
		return "Accompany [id=" + id + ", cid=" + cid + ", isEnd=" + isEnd + ", contractAddress=" + contractAddress
				+ "]";
	}

}
