package com.huge.nearby.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huge.nearby.R;
import com.huge.nearby.activities.NearbyActivity;
import com.huge.nearby.foursquare.ApiRequestResponse;
import com.huge.nearby.foursquare.FoursquareApi;
import com.huge.nearby.foursquare.JSONFieldParser;
import com.huge.nearby.foursquare.Method;
import com.huge.nearby.foursquare.entities.CompactVenue;
import com.huge.nearby.foursquare.entities.VenueGroup;
import com.huge.nearby.utils.FoursquareUtil;


public class RequestNearVenuesTask extends AsyncTask<String, String, CompactVenue[]>{
	
	private NearbyActivity activity = null;
	private ProgressBar progressBar = null;
	
	public RequestNearVenuesTask(NearbyActivity activity) {
		this.activity = activity;
		showProgressBar();
	}

	private boolean skipNonExistingFields = true;

	@Override
	protected CompactVenue[] doInBackground(String... params) {
		String ll = params[0] + "," + params[1];
		return getVenues(ll);

	}

	private CompactVenue[] getVenues(String ll) {
		CompactVenue[] venues = null;
		
		try {
			String url = FoursquareUtil.getFoursquareVenesSearchURL(ll);
			ApiRequestResponse response = new FoursquareApi().doApiRequest(Method.GET, url);
			if (response.getMeta().getCode() == 200) {

				if (response.getResponse().has("groups")) {
					VenueGroup[] groups = null;
					groups = (VenueGroup[]) JSONFieldParser.parseEntities(VenueGroup.class, response.getResponse().getJSONArray("groups"), this.skipNonExistingFields);
					if (groups != null){
						venues = groups[0].getItems();
					}
				} 
			}

		} catch (Exception e) {
			Log.e(FoursquareUtil.FOURQUARE_LOG_TAG, "Error on get venues on foursquare end point", e);
		}
		
		return venues;
	}

	@Override
	protected void onPostExecute(CompactVenue[] result) {
		TextView numberOfPlaces = (TextView) activity.findViewById(R.id.numberOfPlacesNearText);
		numberOfPlaces.setText(String.valueOf(result.length));
		
		hideProgressBar();
	}

	
	private void showProgressBar() {
		progressBar = (ProgressBar) this.activity.findViewById(R.id.progressBar);
		progressBar.setVisibility(ProgressBar.VISIBLE);
	}
	
	private void hideProgressBar() {
		this.progressBar.setVisibility(ProgressBar.INVISIBLE);
	}
}
