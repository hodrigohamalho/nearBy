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
	public static final String FOURSQUARE_CLIENT_ID = "OM5SM5YIYKMPQDUMVOOS3WVMEPD3YFLSEIWW4Y0B5FCUROF2";
	public static final String FOURSQUARE_CLIENT_SECRET = "WZKGW45FIHJZJ4OSOTNQSAPCZRFODJ1UBVCDCIADN1FYIKNY";
	public static final String FOURSQUARE_CALLBACK_URL = "nearby://auth";

	public static final String FOURQUARE_LOG_TAG = "Foursquare";

	// Urls for foursquare services
	public static final String FOURSQUARE_LOGIN_URL = "https://foursquare.com/oauth2/authenticate" +
			"?client_id=" + FoursquareUtil.FOURSQUARE_CLIENT_ID +
			"&response_type=code" +
			"&redirect_uri=" +FoursquareUtil.FOURSQUARE_CALLBACK_URL;


	public static final String getFoursquareAccessTokenUrl(String code){
		return "https://foursquare.com/oauth2/access_token" +
				"?client_id=" + FoursquareUtil.FOURSQUARE_CLIENT_ID +
				"&client_secret=" + FoursquareUtil.FOURSQUARE_CLIENT_SECRET +
				"&grant_type=authorization_code" +
				"&redirect_uri=" + FoursquareUtil.FOURSQUARE_CALLBACK_URL +
				"&code=" + code;
	}

	public static final String getFoursquareUserURL(String token){
		return "https://api.foursquare.com/v2/" +
				"users/self" +
				"?oauth_token=" +token;
	}
	
	public static final String getFoursquareVenesSearchURL(String token, String ll){
		return "https://api.foursquare.com/v2/" +
				"venues/search/" +
				"?ll=" +ll +
				"?oauth_token=" +token;
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
