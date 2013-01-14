package com.huge.nearby.tasks;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.huge.nearby.activities.NearbyActivity;
import com.huge.nearby.activities.WelcomeActivity;
import com.huge.nearby.utils.FoursquareUtil;


public class RequestAcessTokenTask extends AsyncTask<String, String, String>{
	
	private WelcomeActivity welcomeActivity;
	
	public RequestAcessTokenTask(WelcomeActivity welcomeActivity) {
		this.welcomeActivity = welcomeActivity;
	}

	protected String doInBackground(String... params) {
		String token = "";
		
		try {
			JSONObject tokenJson = FoursquareUtil.executeHttpGet(FoursquareUtil.getFoursquareAccessTokenUrl(params[0]));
			token = tokenJson.getString("access_token");
			Log.d(FoursquareUtil.FOURQUARE_LOG_TAG, "Token oauth 4square: token" + token);
		} catch (Exception e) {
			Log.e(FoursquareUtil.FOURQUARE_LOG_TAG, "Error on get access_token", e);
		}

		Intent nearByIntent = new Intent(NearbyActivity.class.getCanonicalName());
		nearByIntent.putExtra("access_token", token);
		welcomeActivity.startActivity(nearByIntent);
		
		return token;
	}
	
}
