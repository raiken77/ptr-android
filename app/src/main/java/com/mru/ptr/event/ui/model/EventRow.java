package com.mru.ptr.event.ui.model;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class EventRow {
  public String meetingTitle;
  public String meetingDate;
  public String meetingThumbnailUrl;

  public String getMeetingTitle() {
    return meetingTitle;
  }

  public EventRow setMeetingTitle(String meetingTitle) {
    this.meetingTitle = meetingTitle;
    return this;
  }

  public String getMeetingDate() {
    return meetingDate;
  }

  public EventRow setMeetingDate(String meetingDate) {
    this.meetingDate = meetingDate;
    return this;
  }

  public String getMeetingThumbnailUrl() {
    return meetingThumbnailUrl;
  }

  public EventRow setMeetingThumbnailUrl(String meetingThumbnailUrl) {
    this.meetingThumbnailUrl = meetingThumbnailUrl;
    return this;
  }
}
