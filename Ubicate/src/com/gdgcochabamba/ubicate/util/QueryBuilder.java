package com.gdgcochabamba.ubicate.util;

public class QueryBuilder {

	
	
	public static String searchPlaceQuery(String lng, String lat, String radius) {
		
		return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
				
		+lng+","+lat+"&radius="+radius+"&sensor=true&key=AIzaSyDfe4hpPEAhvVXIxUBW99xyaZIrc1kYdpI";
		
	}
	
}
