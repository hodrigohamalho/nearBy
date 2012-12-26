package com.huge.nearby.tasks;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.huge.nearby.utils.FoursquareUtil;


public class RequestAcessTokenTask extends AsyncTask<String, String, String>{

	protected String doInBackground(String... params) {
		String token = "";

		try {
			JSONObject tokenJson = FoursquareUtil.executeHttpGet(FoursquareUtil.getFoursquareAccessTokenUrl(params[0]));
			token = tokenJson.getString("access_token");
		} catch (Exception e) {
			Log.e(FoursquareUtil.FOURQUARE_LOG_TAG, "Error on get access_token", e);
		}

		return token;
	}

}
