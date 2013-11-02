package com.gdgcochabamba.ubicate.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.gdgcochabamba.ubicate.exceptions.PlaceRequestException;
import com.gdgcochabamba.ubicate.model.Place;
import com.gdgcochabamba.ubicate.model.User;
import com.gdgcochabamba.ubicate.rest.RestClient;
import com.gdgcochabamba.ubicate.rest.RestConstants;
import com.gdgcochabamba.ubicate.rest.helpers.SearchResult;
import com.gdgcochabamba.ubicate.util.QueryBuilder;
import com.google.gson.Gson;

public class UserController {

	
	public User put(User user) throws ClientProtocolException, IOException{
		
		Gson gson = new Gson();
		
			Reader res = RestClient.PUT(RestConstants.LOGIN,gson.toJson(user));
			user =gson.fromJson(res,User.class);
		
		return user;
		
	}
	
	
//	
//	public List<User> getUsers() throws ClientProtocolException, IOException, PlaceRequestException {
//		List<User> users = new ArrayList<User>();
//		`
//		Gson gson = new Gson();
//		
//		Reader res = RestClient.GET(QueryBuilder.searchPlaceQuery(latitude, longitude, "500"));
//		SearchResult sr = gson.fromJson(res,SearchResult.class);
//		if(sr.status.equals("OK")){
//			
//		
//		
//		return sr.getPlaces(); 
//	}
}
