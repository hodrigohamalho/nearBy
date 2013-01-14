package com.huge.nearby.foursquare.entities;

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

}
