package com.mru.ptr.district.ui.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Created by Jonathan on 2019-10-16.
 */
@Entity(tableName = "candidate",
  foreignKeys = @ForeignKey(
    entity = DistrictDataModel.class,
    parentColumns = "id",
    childColumns = "district_id"
  )
)
public class CandidateDataModel {
  @PrimaryKey
  @ColumnInfo(name = "candidate_id")
  @NonNull
  public String candidateId;

  public String name;

  @ColumnInfo(name = "family_name")
  public String familyName;

  @ColumnInfo(name = "image_url")
  public String imageUrl;

  public String party;

  public String description;

  @ColumnInfo(name = "district_id")
  public String districtId;


  public String getDistrictId() {
    return districtId;
  }

  public CandidateDataModel setDistrictId(String districtId) {
    this.districtId = districtId;
    return this;
  }

  public String getCandidateId() {
    return candidateId;
  }

  public CandidateDataModel setCandidateId(String candidateId) {
    this.candidateId = candidateId;
    return this;
  }

  public String getName() {
    return name;
  }

  public CandidateDataModel setName(String name) {
    this.name = name;
    return this;
  }

  public String getFamilyName() {
    return familyName;
  }

  public CandidateDataModel setFamilyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public CandidateDataModel setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public String getParty() {
    return party;
  }

  public CandidateDataModel setParty(String party) {
    this.party = party;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public CandidateDataModel setDescription(String description) {
    this.description = description;
    return this;
  }
}
