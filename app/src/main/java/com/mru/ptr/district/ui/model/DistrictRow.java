package com.mru.ptr.district.ui.model;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class DistrictRow {
  public String districtNumber;
  public String candidatesBackgroundImageUrl;

  public String getDistrictNumber() {
    return districtNumber;
  }

  public DistrictRow setDistrictNumber(String districtNumber) {
    this.districtNumber = districtNumber;
    return this;
  }

  public String getCandidatesBackgroundImageUrl() {
    return candidatesBackgroundImageUrl;
  }

  public DistrictRow setCandidatesBackgroundImageUrl(String candidatesBackgroundImageUrl) {
    this.candidatesBackgroundImageUrl = candidatesBackgroundImageUrl;
    return this;
  }
}
