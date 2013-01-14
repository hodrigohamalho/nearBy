package com.huge.nearby.foursquare.entities;

import com.huge.nearby.foursquare.FoursquareEntity;

public class Contact implements FoursquareEntity {
  private static final long serialVersionUID = -7810041187718129997L;
  
  private String email;
  private String facebook;
  private String twitter;
  private String phone;
  
  public String getPhone() {
    return phone;
  }
  
  public String getTwitter() {
    return twitter;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getFacebook() {
    return facebook;
  }
  
}
