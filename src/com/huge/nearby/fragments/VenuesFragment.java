package com.huge.nearby.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huge.nearby.R;
import com.huge.nearby.entities.Direction;
import com.huge.nearby.foursquare.entities.CompactVenue;

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
		
		TextView venueName = (TextView) frag.getActivity().findViewById(R.id.venueNameText); 
		TextView website = (TextView) frag.getActivity().findViewById(R.id.websiteText); 
		TextView address = (TextView) frag.getActivity().findViewById(R.id.addressText);
		ImageView image = (ImageView) frag.getActivity().findViewById(R.id.imageView1);
		
		image.setImageBitmap(venue.getImagem());
		venueName.setText(venue.getName());
		website.setText(venue.getUrl());
		address.setText(venue.getLocation().getAddress());		
	}


}
