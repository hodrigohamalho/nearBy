package com.huge.nearby.activities;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.Window;

import com.huge.nearby.R;
import com.huge.nearby.geo.LocationUtil;
import com.huge.nearby.geo.NearByLocationListener;
import com.huge.nearby.tasks.RequestAcessTokenTask;
import com.huge.nearby.tasks.RequestNearVenuesTask;
import com.huge.nearby.utils.FoursquareUtil;

public class NearbyActivity extends FragmentActivity {

	private LocationManager locationManager;
	private LocationListener locationListener = new NearByLocationListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		removeTitleBar();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearby);
		loginToForsquare();
	}

	
	
	private Location getActualLocation() {
		Location gpsLocation = null;
		Location networkLocation = null;
		Location location = null;
		this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Request updates from both fine (gps) and coarse (network) providers.
		gpsLocation = LocationUtil.requestUpdatesFromProvider(this.locationManager, locationListener,
				LocationManager.GPS_PROVIDER, R.string.not_support_gps);
		networkLocation = LocationUtil.requestUpdatesFromProvider(this.locationManager, locationListener,
				LocationManager.NETWORK_PROVIDER, R.string.not_support_network);

		// If both providers return last known locations, compare the two and use the better
		// one to update the UI.  If only one provider returns a location, use it.
		if (gpsLocation != null && networkLocation != null) {
			location = LocationUtil.getBetterLocation(gpsLocation, networkLocation);
		} else if (gpsLocation != null) {
			location = gpsLocation;
		} else if (networkLocation != null) {
			location = networkLocation;
		}

		return location;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_nearby, menu);
		return true;
	}

	private void removeTitleBar() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	private void loginToForsquare() {
		// Call the webbrowser with the Foursquare OAuth login URL
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(FoursquareUtil.FOURSQUARE_LOGIN_URL));
		startActivity(intent);
	}
	
	public void onResume() {
		super.onResume();

		// extract the OAUTH access token if it exists
	    Uri uri = this.getIntent().getData();
	    if(uri != null) {
	    	String code = uri.getQueryParameter("code");
	    	
	    	// Check if there is a parameter called "code"
	    	if(code == null){
	    		new RequestAcessTokenTask().execute(code);
	    	}else if (code != null){
	    		Location location = getActualLocation();
	    		String latitudeLongitude = location.getLatitude() + "," + location.getLongitude();
	    		new RequestNearVenuesTask().execute(latitudeLongitude, code);
	    	}
	    }
	}

}

