package com.mru.ptr.manifesto.ui.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Jonathan on 2019-10-16.
 */
@Entity(tableName = "manifesto",
  foreignKeys = @ForeignKey(
    entity = ManifestoCategoryDataModel.class,
    parentColumns = "id",
    childColumns = "manifesto_category_id",
    onDelete = CASCADE
  )
)
public class ManifestoDataModel {

  @PrimaryKey
  @NonNull
  public String manifestoId;

  @ColumnInfo(name = "image_url")
  public String imageUrl;

  public String title;

  public String description;

  @ColumnInfo(name = "manifesto_category_id")
  public String manifestoCategoryId;

  @Ignore
  public int drawableRes;

  public String getManifestoId() {
    return manifestoId;
  }

  public ManifestoDataModel setManifestoId(String manifestoId) {
    this.manifestoId = manifestoId;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ManifestoDataModel setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public ManifestoDataModel setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ManifestoDataModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public int getDrawableRes() {
    return drawableRes;
  }

  public ManifestoDataModel setDrawableRes(int drawableRes) {
    this.drawableRes = drawableRes;
    return this;
  }

  public String getManifestoCategoryId() {
    return manifestoCategoryId;
  }

  public ManifestoDataModel setManifestoCategoryId(String manifestoCategoryId) {
    this.manifestoCategoryId = manifestoCategoryId;
    return this;
  }
}
