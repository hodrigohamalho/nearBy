package com.huge.nearby.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class FoursquareUtil {

	// Credentials
	public static final String CLIENT_ID = "OM5SM5YIYKMPQDUMVOOS3WVMEPD3YFLSEIWW4Y0B5FCUROF2";
	public static final String CLIENT_SECRET = "WZKGW45FIHJZJ4OSOTNQSAPCZRFODJ1UBVCDCIADN1FYIKNY";
	public static final String CALLBACK_URL = "nearby://auth";
	
	private static final String API_URL = "https://api.foursquare.com/v2/";

	public static final String FOURQUARE_LOG_TAG = "Foursquare";

	public static final String FOURSQUARE_LOGIN_URL = "https://foursquare.com/oauth2/authenticate" +
			"?client_id=" + FoursquareUtil.CLIENT_ID +
			"&response_type=code" +
			"&redirect_uri=" +FoursquareUtil.CALLBACK_URL;


	public static final String getFoursquareAccessTokenUrl(String code){
		return "https://foursquare.com/oauth2/access_token" +
				"?client_id=" + FoursquareUtil.CLIENT_ID +
				"&client_secret=" + FoursquareUtil.CLIENT_SECRET +
				"&grant_type=authorization_code" +
				"&redirect_uri=" + FoursquareUtil.CALLBACK_URL +
				"&code=" + code;
	}

	public static final String getFoursquareUserURL(String token){
		return API_URL +
				"users/self" +
				"?oauth_token=" +token;
	}
	
	public static final String getFoursquareVenesSearchURL(String ll){
		return API_URL +
				"venues/search" +
				"?client_id=" + FoursquareUtil.CLIENT_ID +
				"&client_secret=" + FoursquareUtil.CLIENT_SECRET +
				"&ll=" +ll;			
	}
	

	//Calls a URI and returns the answer as a JSON object
	public static JSONObject executeHttpGet(String uri) throws Exception{
		HttpGet req = new HttpGet(uri);

		HttpClient client = new DefaultHttpClient();
		HttpResponse resLogin = client.execute(req);
		BufferedReader r = new BufferedReader(
				new InputStreamReader(resLogin.getEntity()
						.getContent()));
		StringBuilder sb = new StringBuilder();
		String s = null;
		while ((s = r.readLine()) != null) {
			sb.append(s);
		}

		return new JSONObject(sb.toString());
	}
}
