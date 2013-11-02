package com.gdgcochabamba.ubicate.rest.helpers;

public class PlaceHelper {

	public static String newPlaceToJson(String longitude, String latitude, String name, String type) {
		
		return "{\"location\": {\"lat\":"+latitude+",\"lng\":"+longitude+"},\"accuracy\": 50,\"name\": \" "+name+"\",\"types\": [\""+type.replace(" ", "_")+"\"],\"language\": \"en-AU\"}";
		
	}
	
}
