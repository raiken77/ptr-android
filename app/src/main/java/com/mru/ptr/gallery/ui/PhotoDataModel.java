package com.mru.ptr.gallery.ui;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Jonathan on 2019-10-16.
 */
@Entity(tableName = "photo")
public class PhotoDataModel {

  @PrimaryKey
  @NonNull
  public String imageId;

  public String pictureUrl;

  public String getImageId() {
    return imageId;
  }

  public PhotoDataModel setImageId(String imageId) {
    this.imageId = imageId;
    return this;
  }

  public String getImageUrl() {
    return pictureUrl;
  }

  public PhotoDataModel setImageUrl(String imageUrl) {
    this.pictureUrl = imageUrl;
    return this;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public PhotoDataModel setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
    return this;
  }
}
