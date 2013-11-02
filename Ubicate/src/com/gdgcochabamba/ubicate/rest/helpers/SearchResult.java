package com.gdgcochabamba.ubicate.rest.helpers;

import java.util.List;

import android.R.string;

import com.gdgcochabamba.ubicate.model.Place;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

	@SerializedName("status")
	public String status;
	
	@SerializedName("debug_info")
	public String[] debugInfo;
	
	@SerializedName("html_attributions")
	public String[] htmlAttributions;
	
	@SerializedName("next_page_token")
	public String nextPageToken;
	
	@SerializedName("results")
	public List<Place> places;

	public String[] getDebugInfo() {
		return debugInfo;
	}

	public void setDebugInfo(String[] debugInfo) {
		this.debugInfo = debugInfo;
	}

	public String[] getHtmlAttributions() {
		return htmlAttributions;
	}

	public void setHtmlAttributions(String[] htmlAttributions) {
		this.htmlAttributions = htmlAttributions;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
