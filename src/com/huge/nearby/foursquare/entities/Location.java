package com.huge.nearby.foursquare.entities;

import com.huge.nearby.foursquare.FoursquareEntity;


public class Location implements FoursquareEntity {
  private static final long serialVersionUID = -76729758415926344L;
  
  private String address;
  private String crossStreet;
  private String city;
  private String state;
  private String postalCode;
  private String country;
  private String name;
  private Double lat;
  private Double lng;
  private Double distance;
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address){
	  this.address = address;
  }

  public String getCrossStreet() {
    return crossStreet;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCountry() {
    return country;
  }

  public String getName() {
    return name;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLng() {
    return lng;
  }

  public Double getDistance() {
    return distance;
  }
  
  public void setDistance(Double distance){
	  this.distance = distance;
  }

}
