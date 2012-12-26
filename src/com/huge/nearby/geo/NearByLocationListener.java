package com.huge.nearby.geo;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class NearByLocationListener implements LocationListener{
	
	private final static String TAG = "GEO";

	@Override
	public void onLocationChanged(Location location) {
		Log.d(TAG, "Getting current location...");
		location.getLatitude();
		location.getLongitude();

		String text = "My current location is: " +
		"Latitud = " + location.getLatitude() +
		"Longitud = " + location.getLongitude();

		Log.d(TAG, text);
	}

	@Override
	public void onProviderDisabled(String provider) {
		String text = "GPS desligado!";
		Log.d(TAG, text);
	}

	@Override
	public void onProviderEnabled(String provider) {
		String text = "GPS ligado!";
		Log.d(TAG, text);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
