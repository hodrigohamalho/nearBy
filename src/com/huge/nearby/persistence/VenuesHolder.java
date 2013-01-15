package com.huge.nearby.persistence;

import com.huge.nearby.foursquare.entities.CompactVenue;

public class VenuesHolder {

	public VenuesHolder(CompactVenue[] venues, int index){
		VenuesHolder.venues = venues;
		VenuesHolder.index = index;
	}

	private static CompactVenue[] venues = null;
	private static int index = -1; 

	public static CompactVenue getVenue() {
		return venues[index];
	}

	public static int venuesCount(){
		return venues.length;
	}

	public static int getIndex(){
		return index;
	}

	public static int incrementIndex(){
		if (index != VenuesHolder.venuesCount()-1){
			VenuesHolder.index += 1;
		}

		return index;
	}

	public static int decrementIndex(){
		if (index > 0){
			VenuesHolder.index -= 1;
		}

		return index;
	}

}
