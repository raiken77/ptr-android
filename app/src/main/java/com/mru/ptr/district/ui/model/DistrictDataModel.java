package com.mru.ptr.district.ui.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Jonathan on 2019-10-10.
 */
@Entity(tableName = "district")
public class DistrictDataModel {

  @PrimaryKey
  @NonNull
  public String id;
  public String name;


  public String getId() {
    return id;
  }

  public DistrictDataModel setId(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public DistrictDataModel setName(String name) {
    this.name = name;
    return this;
  }
}
