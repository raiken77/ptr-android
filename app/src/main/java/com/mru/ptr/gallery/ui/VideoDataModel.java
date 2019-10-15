package com.mru.ptr.gallery.ui;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Jonathan on 2019-10-16.
 */
@Entity(tableName = "video")
public class VideoDataModel {

  @PrimaryKey
  @NonNull
  public String videoId;

  @ColumnInfo(name = "thumbnail_url")
  public String thumbnailUrl;
  public String title;

  @ColumnInfo(name = "video_url")
  public String videoUrl;

  @Ignore
  public int drawableRes;

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public VideoDataModel setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public VideoDataModel setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public VideoDataModel setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
    return this;
  }

  public int getDrawableRes() {
    return drawableRes;
  }

  public VideoDataModel setDrawableRes(int drawableRes) {
    this.drawableRes = drawableRes;
    return this;
  }
}
