package com.gdgcochabamba.ubicate.controller;

import java.io.IOException;
import java.io.Reader;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.gdgcochabamba.ubicate.model.Checkin;
import com.gdgcochabamba.ubicate.rest.RestClient;
import com.gdgcochabamba.ubicate.rest.RestConstants;
import com.google.gson.Gson;

public class CheckinController {
	
	public Checkin postCheckin(Checkin checkin) throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		Log.i("CHECKIN", gson.toJson(checkin));
		Reader res = RestClient.PUT(RestConstants.CHECKIN, gson.toJson(checkin));
		checkin = gson.fromJson(res,Checkin.class);
		
		return checkin;
	}

}
