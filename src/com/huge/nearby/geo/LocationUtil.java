package com.huge.nearby.geo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class LocationUtil {

	private static final int TWO_MINUTES = 1000 * 60 * 2;
    private static final int RUN_ONCE = 1000000000;

	/** Determines whether one Location reading is better than the current Location fix.
	 * Code taken from
	 * http://developer.android.com/guide/topics/location/obtaining-user-location.html
	 *
	 * @param newLocation  The new Location that you want to evaluate
	 * @param currentBestLocation  The current Location fix, to which you want to compare the new
	 *        one
	 * @return The better Location object based on recency and accuracy.
	 */
	public static Location getBetterLocation(Location newLocation, Location currentBestLocation) {
		if (currentBestLocation == null) {
			// A new location is always better than no location
			return newLocation;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = newLocation.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use the new location
		// because the user has likely moved.
		if (isSignificantlyNewer) {
			return newLocation;
			// If the new location is more than two minutes older, it must be worse
		} else if (isSignificantlyOlder) {
			return currentBestLocation;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (newLocation.getAccuracy() - currentBestLocation.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(newLocation.getProvider(),
				currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and accuracy
		if (isMoreAccurate) {
			return newLocation;
		} else if (isNewer && !isLessAccurate) {
			return newLocation;
		} else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
			return newLocation;
		}

		return currentBestLocation;
	}

    /** Checks whether two providers are the same */
    public static boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
          return provider2 == null;
        }
        return provider1.equals(provider2);
    }
    
    /**
     * Method to register location updates with a desired location provider.  If the requested
     * provider is not available on the device, the app displays a Toast with a message referenced
     * by a resource id.
     *
     * @param provider Name of the requested provider.
     * @param errorResId Resource id for the string message to be displayed if the provider does
     *                   not exist on the device.
     * @return A previously returned {@link android.location.Location} from the requested provider,
     *         if exists.
     */
    public static Location requestUpdatesFromProvider(LocationManager locationManager,
    		LocationListener locationListener, final String provider, final int errorResId) {
        Location location = null;
        
        if (locationManager.isProviderEnabled(provider)) {
            locationManager.requestLocationUpdates(provider, RUN_ONCE, RUN_ONCE, locationListener);
            location = locationManager.getLastKnownLocation(provider);
        } 
        
        return location;
    }
	
}
