package com.gdgcochabamba.ubicate.util;

public class Utility {

public static String[] getPlacesTypes(String[] places) {

	String[] resultPlaces = new String[places.length];
	for (int i = 0; i < places.length; i++) {
		resultPlaces[i] = places[i].replace("_", " ");
	}
	return resultPlaces;
}
}
