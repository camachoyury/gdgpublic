package com.gdgcochabamba.ubicate.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.SocketException;

import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class RestClient {

	public static Reader PUT(String URL, String json)
			throws ClientProtocolException, IOException {
		Reader r;

		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 5000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		HttpPut httpPut = new HttpPut(URL);
		// CDebugger.debugLog("Login",
		// LoginActivity.LoadStringPreferences(SessionHelper.LOGIN));
		// CDebugger.debugLog("Password",
		// LoginActivity.LoadStringPreferences(SessionHelper.PASSWORD));
		// CDebugger.debugLog("URL",URL);

		httpPut.setHeader("content-type", "application/json");
		// httpPost.setHeader(SessionHelper.LOGIN,
		// LoginActivity.LoadStringPreferences(SessionHelper.LOGIN));
		// httpPost.setHeader(SessionHelper.PASSWORD,
		// LoginActivity.LoadStringPreferences(SessionHelper.PASSWORD));
		//
		httpPut.setEntity(new StringEntity(json));

		HttpResponse response = httpclient.execute(httpPut);
		StatusLine sl = response.getStatusLine();
		int statCode = sl.getStatusCode();
		if (statCode == 200) {
			r = new InputStreamReader(response.getEntity().getContent());
		} else {

			r = null;
		}

		return r;

	}

	public static Reader GET(String url) throws ClientProtocolException, IOException {
		Reader r = null;
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 5000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		
		
		
//		HttpClient httpClient = new DefaultHttpClient();
		Log.i("GEt METHOD", "entering");
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("content-type", "application/json");
		//
		// CDebugger.debugLog("Login",
		// LoginActivity.LoadStringPreferences(SessionHelper.LOGIN));
		// CDebugger
		// .debugLog("Password", LoginActivity
		// .LoadStringPreferences(SessionHelper.PASSWORD));
		// CDebugger.debugLog("URL", url);
		//
		// httpGet.setHeader(SessionHelper.LOGIN,
		// LoginActivity.LoadStringPreferences(SessionHelper.LOGIN));
		// httpGet.setHeader(SessionHelper.PASSWORD,
		// LoginActivity.LoadStringPreferences(SessionHelper.PASSWORD));

		Log.i("HttpGet ", httpGet.toString());
		HttpResponse response = httpClient.execute(httpGet);
		r = new InputStreamReader(response.getEntity().getContent());

		Log.i("RESPONSE REST", r.toString());
		// boolean execute=false;
		// if(execute){
//		try {
//			BufferedReader in = new BufferedReader(r);
//			StringBuffer sb = new StringBuffer("");
//			String line = "";
//			String NL = System.getProperty("line.separator");
//			while ((line = in.readLine()) != null) {
//				sb.append(line + NL);
//			}
//			String page = sb.toString();
//			Log.i("response ", page);
//		} catch (Exception ex) {
//		}
		// execute=true;

		return r;

	}
	
	
	
	
	
	public static Reader POST(String URL, String json)
			throws ClientProtocolException, IOException {
		Reader r;

		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 5000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		HttpPost httpPost = new HttpPost(URL);
		// CDebugger.debugLog("Login",
		// LoginActivity.LoadStringPreferences(SessionHelper.LOGIN));
		// CDebugger.debugLog("Password",
		// LoginActivity.LoadStringPreferences(SessionHelper.PASSWORD));
		// CDebugger.debugLog("URL",URL);

		httpPost.setHeader("content-type", "application/json");
		// httpPost.setHeader(SessionHelper.LOGIN,
		// LoginActivity.LoadStringPreferences(SessionHelper.LOGIN));
		// httpPost.setHeader(SessionHelper.PASSWORD,
		// LoginActivity.LoadStringPreferences(SessionHelper.PASSWORD));
		//
		httpPost.setEntity(new StringEntity(json));

		HttpResponse response = httpclient.execute(httpPost);
		StatusLine sl = response.getStatusLine();
		int statCode = sl.getStatusCode();
		if (statCode == 200) {
			r = new InputStreamReader(response.getEntity().getContent());
//			try{
//				BufferedReader  in = new BufferedReader
//		              (r);
//		                StringBuffer sb = new StringBuffer("");
//		                String line = "";
//		                String NL = System.getProperty("line.separator");
//		               while ((line = in.readLine()) != null) {
//		                    sb.append(line + NL);
//		                }
//		                String page = sb.toString();
//				Log.i("response ",page);
//				}catch(Exception e){}	
		} else {

			r = null;
		}

		return r;

	}
}
