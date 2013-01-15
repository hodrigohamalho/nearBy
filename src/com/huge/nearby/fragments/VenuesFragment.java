package com.huge.nearby.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.huge.nearby.R;
import com.huge.nearby.entities.Direction;
import com.huge.nearby.foursquare.entities.CompactVenue;
import com.huge.nearby.persistence.VenuesHolder;

public class VenuesFragment extends Fragment{

	private float y = -1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View venuesView = inflater.inflate(R.layout.venues_fragment, container, false);

		venuesView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				boolean return_flag = false;

				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						y = event.getY();
						return true;
					case MotionEvent.ACTION_UP:
						float dy = y - event.getY();
						
						if (dy < 0){
							changeVenue(Direction.DOWN);
						}else{
							changeVenue(Direction.UP);
						}
					}

				return return_flag;
			}
		});

		return venuesView;
	}
	
	private void changeVenue(Direction direction){
		CompactVenue venue = null;
		
		if (direction.equals(Direction.UP)){
			VenuesHolder.incrementIndex(); 
			venue = VenuesHolder.getVenue();
		}else{
			VenuesHolder.decrementIndex();
			venue = VenuesHolder.getVenue();
		}
		
		updateVenueOnView(venue, null);
	}

	public void updateVenueOnView(CompactVenue venue, Fragment frag) {
		if (frag == null){
			frag = this;
		}
		
		venue.fillEmptyValues();
		venue.updateFieldsOnView(frag);
	}

}
