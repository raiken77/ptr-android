package com.mru.ptr.manifesto.ui.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Jonathan on 2019-10-16.
 */
@Entity(tableName = "manifesto_category")
public class ManifestoCategoryDataModel {

  @PrimaryKey
  @NonNull
  public String id;

  public String name;

  @ColumnInfo(name = "image_url")
  public String imageUrl;

  @Ignore
  public int drawableRes;

  public String getId() {
    return id;
  }

  public ManifestoCategoryDataModel setId(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ManifestoCategoryDataModel setName(String name) {
    this.name = name;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ManifestoCategoryDataModel setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getDrawableRes() {
    return drawableRes;
  }

  public ManifestoCategoryDataModel setDrawableRes(int drawableRes) {
    this.drawableRes = drawableRes;
    return this;
  }
}
