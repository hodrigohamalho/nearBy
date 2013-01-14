package com.huge.nearby.foursquare.entities;

import com.huge.nearby.foursquare.FoursquareEntity;

public class Category implements FoursquareEntity {
  private static final long serialVersionUID = -4573082152802069375L;
  
  private String id;
  private String name;
  private String pluralName;
  private String icon;
  private String[] parents;
  private Boolean primary;
  private Category[] categories;
  
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public String getPluralName() {
    return pluralName;
  }

  public String getIcon() {
    return icon;
  }

  public String[] getParents() {
    return parents;
  }

  public Boolean getPrimary() {
    return primary;
  }
  
  public Category[] getCategories() {
    return categories;
  }

}
