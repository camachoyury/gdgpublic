package com.gdgcochabamba.ubicate.model;

import com.google.gson.annotations.SerializedName;

public class Checkin {
	@SerializedName("id")
	private long id;
	@SerializedName("place_id")
	private String placeReference;
	
	@SerializedName("user_id")
	private String userId;
	@SerializedName("created_at")
	private String date;
	@SerializedName("comment")
	private String comment;
	
	
	public Checkin(String placeReference, String userId, String comment) {
		super();
		this.placeReference = placeReference;
		this.userId = userId;
		this.comment = comment;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getPlaceReference() {
		return placeReference;
	}
	public void setPlaceReference(String placeReference) {
		this.placeReference = placeReference;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Checkin [id=" + id + ", placeReference=" + placeReference
				+ ", userId=" + userId + ", date=" + date + "]";
	}
	
	

}
