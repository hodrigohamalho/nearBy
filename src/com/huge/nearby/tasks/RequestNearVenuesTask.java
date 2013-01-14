package com.huge.nearby.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.huge.nearby.foursquare.ApiRequestResponse;
import com.huge.nearby.foursquare.FoursquareApi;
import com.huge.nearby.foursquare.JSONFieldParser;
import com.huge.nearby.foursquare.Method;
import com.huge.nearby.foursquare.entities.CompactVenue;
import com.huge.nearby.foursquare.entities.VenueGroup;
import com.huge.nearby.utils.FoursquareUtil;


public class RequestNearVenuesTask extends AsyncTask<String, String, String>{

	private boolean skipNonExistingFields = true;

	@Override
	protected String doInBackground(String... params) {
		String latitude = params[0];
		String longitude = params[1];
		String authCode = params[2];

		VenueGroup[] groups = null;
		CompactVenue[] venues = null;

		String ll = latitude + "," + longitude;

		try {
			String url = FoursquareUtil.getFoursquareVenesSearchURL(authCode, ll);
			ApiRequestResponse response = new FoursquareApi().doApiRequest(Method.GET, url);
			if (response.getMeta().getCode() == 200) {

				if (response.getResponse().has("groups")) {
					groups = (VenueGroup[]) JSONFieldParser.parseEntities(VenueGroup.class, response.getResponse().getJSONArray("groups"), this.skipNonExistingFields);
					venues = groups[0].getItems();
					for (CompactVenue compactVenue : venues) {
						System.out.println("Name: "+compactVenue.getName());
					}
				} 
				
				
			}

		} catch (Exception e) {
			Log.e(FoursquareUtil.FOURQUARE_LOG_TAG, "Error on get access_token", e);
		}

		return "s";

	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.d("GEO", "executei");
		//Do anything with response..
	}

}
