package com.gdgcochabamba.ubicate.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.gdgcochabamba.ubicate.MainActivity;
import com.gdgcochabamba.ubicate.exceptions.PlaceRequestException;
import com.gdgcochabamba.ubicate.model.Place;
import com.gdgcochabamba.ubicate.model.User;
import com.gdgcochabamba.ubicate.rest.RestClient;
import com.gdgcochabamba.ubicate.rest.RestConstants;
import com.gdgcochabamba.ubicate.rest.helpers.SearchResult;
import com.gdgcochabamba.ubicate.util.QueryBuilder;
import com.google.gson.Gson;

public class PlaceController {

	
	public List<Place> findPlacesByProximity(String latitude, String longitude) throws ClientProtocolException, IOException, PlaceRequestException {
		List<Place> places = new ArrayList<Place>();
		
		Gson gson = new Gson();
		
		Reader res = RestClient.GET(QueryBuilder.searchPlaceQuery(latitude, longitude, "500"));
		
		SearchResult sr = gson.fromJson(res,SearchResult.class);
		if(sr.status.equals("OK")){
			
		}
		 else if(sr.status.equals("ZERO_RESULTS")){
             // Zero results found
             throw new PlaceRequestException("Near Places, Sorry no places found. Try to change the types of places");
         }
         else if(sr.status.equals("UNKNOWN_ERROR"))
         {
        	 throw new PlaceRequestException( "Places Error, Sorry unknown error occured.");
         }
         else if(sr.status.equals("OVER_QUERY_LIMIT"))
         {
        	 throw new PlaceRequestException( "Places Error, Sorry query limit to google places is reached.");
         }
         else if(sr.status.equals("REQUEST_DENIED"))
         {
        	 throw new PlaceRequestException( "Places Error, Sorry error occured. Request is denied.");
         }
         else if(sr.status.equals("INVALID_REQUEST"))
         {
        	 throw new PlaceRequestException( "Places Error, Sorry error occured. Invalid Request");
                     
         }
         else
         {
        	 throw new PlaceRequestException("Places Error, Sorry error occured.");
         }
		
		return sr.getPlaces(); 
	}
	
	public String addNewPlaceInPlaces(String newPlace) throws ClientProtocolException, IOException {
		String reference ="";
		Gson gson = new Gson();
		Reader res = RestClient.POST(RestConstants.ADD_NEW_PLACE, newPlace);
		if (res!=null) {
			Place place = gson.fromJson(res,Place.class);
			Log.d("NEW PLACE",place.toString());
			reference = place.reference;
			addPlaceReference(place);
		}
		return reference;
	}
	
public void addPlaceReference(Place place) throws ClientProtocolException, IOException {
	
		Gson gson = new Gson();
		String userId = MainActivity.instance.loadStringPreferences("USER_ID");
		place.setUserId(userId);
		Reader res = RestClient.PUT(RestConstants.PLACE, gson.toJson(place));
		if (res!=null) {
			
			Log.d("NEW PLACE",place.toString());
		}
		
	}
	
}
