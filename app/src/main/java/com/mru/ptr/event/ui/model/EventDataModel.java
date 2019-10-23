package com.mru.ptr.event.ui.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Jonathan on 2019-10-14.
 */
@Entity(tableName = "event")
public class EventDataModel {

  @PrimaryKey
  @NonNull
  public String eventId;

  public long dateTime;

  public String description;

  public String state;

  public String title;


  public String getEventId() {
    return eventId;
  }


  public EventDataModel setEventId(String eventId) {
    this.eventId = eventId;
    return this;
  }

  public long getDateTime() {
    return dateTime;
  }

  public EventDataModel setDateTime(long dateTime) {
    this.dateTime = dateTime;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public EventDataModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getState() {
    return state;
  }

  public EventDataModel setState(String state) {
    this.state = state;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public EventDataModel setTitle(String title) {
    this.title = title;
    return this;
  }

}
