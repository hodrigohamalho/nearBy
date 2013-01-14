package com.huge.nearby.foursquare.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Count implements FoursquareEntity {

  private static final long serialVersionUID = -471761138324979612L;

  public Long getCount() {
    return count;
  }
  
  private Long count;
}
