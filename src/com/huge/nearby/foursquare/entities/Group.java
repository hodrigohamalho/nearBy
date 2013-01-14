package com.huge.nearby.foursquare.entities;

import com.huge.nearby.foursquare.FoursquareEntity;

public abstract class Group<T extends FoursquareEntity> extends Count {

  private static final long serialVersionUID = -3156890964170514232L;

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }
  
  public abstract T[] getItems();

  private String type;
  private String name;
}
