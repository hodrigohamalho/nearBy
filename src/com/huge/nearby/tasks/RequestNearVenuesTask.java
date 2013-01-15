package com.huge.nearby.tasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.huge.nearby.fragments.VenuesFragment;
import com.huge.nearby.io.ImageSize;
import com.huge.nearby.persistence.VenuesHolder;
import com.huge.nearby.utils.FoursquareUtil;


public class RequestNearVenuesTask extends AsyncTask<String, String, CompactVenue[]>{

	private NearbyActivity activity = null;
	private String ll = null;
	private ProgressBar progress = null;
	private boolean skipNonExistingFields = true;
	private int attempts = 0;

	public RequestNearVenuesTask(NearbyActivity activity) {
		this.activity = activity;
		showProgressBar();
	}


	@Override
	protected CompactVenue[] doInBackground(String... params) {
		ll = params[0] + "," + params[1];
		return getVenues();

	}

	private CompactVenue[] getVenues() {
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

			parseUrlToBitmap(venues);

			new VenuesHolder(venues, 0);
		} catch (Exception e) {
			Log.e(FoursquareUtil.FOURQUARE_LOG_TAG, "Error on get venues on foursquare end point", e);
			if (attempts < 3){
				attempts ++;
				getVenues();
			}
		}

		return venues;
	}

	@Override
	protected void onPostExecute(CompactVenue[] venues) {
		TextView numberOfPlaces = (TextView) activity.findViewById(R.id.numberOfPlacesNearText);
		numberOfPlaces.setText(String.valueOf(VenuesHolder.venuesCount()));

		new VenuesFragment().updateVenueOnView(VenuesHolder.getVenue(), activity.getFragmentManager().findFragmentById(R.id.venue_fragment));
		hideProgressBar();
		attempts = 0;
	}

	private void parseUrlToBitmap(CompactVenue[] venues){
		Bitmap image = null;

		for (CompactVenue venue : venues) {
			try {
				if (venue.getCategories() != null && venue.getCategories().length > 0){
					String url = getFoursquareImageURL(ImageSize.LARGE, venue.getCategories()[0].getIcon());
					image = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
				} 
			} catch (MalformedURLException e) {
				Log.d(FoursquareUtil.FOURQUARE_LOG_TAG, e.getMessage());
			} catch (IOException e) {
				Log.d(FoursquareUtil.FOURQUARE_LOG_TAG, e.getMessage());
			}

			if (image != null){
				venue.setImage(image);
			}
		}
	}

	private String getFoursquareImageURL(ImageSize size, String icon) {
		String url = null;

		if (icon != null && !icon.isEmpty()){
			if (size.equals(ImageSize.SMALL)){
				return icon;
			}else if (size.equals(ImageSize.MIDDLE)){
				url = icon.substring(0, icon.length()-4);
				url += "_64.png";
			}else{
				url = icon.substring(0, icon.length()-4);
				url += "_256.png";
			}
		}

		return url;
	}

	private void showProgressBar() {
		progress = (ProgressBar) this.activity.findViewById(R.id.progressBar);
		progress.setVisibility(ProgressBar.VISIBLE);
	}

	private void hideProgressBar() {
		this.progress.setVisibility(ProgressBar.INVISIBLE);
	}

}
