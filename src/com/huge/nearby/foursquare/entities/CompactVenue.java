package com.huge.nearby.foursquare.entities;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.huge.nearby.R;
import com.huge.nearby.foursquare.FoursquareEntity;

public class CompactVenue implements FoursquareEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private Boolean verified;
	private Contact contact;
	private Location location;
	private Category[] categories;
	private String url;
	private Bitmap image;
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean getVerified() {
		return verified;
	}

	public Contact getContact() {
		return contact;
	}

	public Location getLocation() {
		return location;
	}

	public Category[] getCategories() {
		return categories;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public void fillEmptyValues() {
		if (getName() == null || getName().isEmpty())
			this.name = "Name unavailable";
		
		if (getUrl() == null || getUrl().isEmpty())
			this.url = "Website unavailable";
		
		if (getLocation().getAddress() == null || getLocation().getAddress().isEmpty())
			this.location.setAddress("Address unavailable");
		
		if (getLocation().getDistance() == null || getLocation().getDistance() != 0)
			this.location.setDistance(Double.valueOf(0));
		
	}

	public void updateFieldsOnView(Fragment frag) {
		TextView venueName = (TextView) frag.getActivity().findViewById(R.id.venueNameText); 
		TextView website = (TextView) frag.getActivity().findViewById(R.id.websiteText); 
		TextView address = (TextView) frag.getActivity().findViewById(R.id.addressText);
		TextView distance = (TextView) frag.getActivity().findViewById(R.id.distanceText);
		ImageView image = (ImageView) frag.getActivity().findViewById(R.id.imageView1);
		
		venueName.setText(getName());
		image.setImageBitmap(getImage());
		website.setText(getUrl());
		address.setText(getLocation().getAddress());		
		distance.setText(String.valueOf(getLocation().getDistance()) + "m");
	}
	
}
