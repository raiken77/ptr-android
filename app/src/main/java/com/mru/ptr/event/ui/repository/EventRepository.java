package com.mru.ptr.event.ui.repository;

import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.event.ui.repository.webservice.EventWebService;
import com.mru.ptr.event.ui.repository.webservice.EventWebServiceCallback;
import com.mru.ptr.event.ui.repository.webservice.FirebaseEventWebService;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-14.
 */
public class EventRepository implements EventWebServiceCallback {

  private EventWebService eventWebService;
  public MutableLiveData<List<EventDataModel>> events = new MutableLiveData<>();


  public EventRepository() {
    eventWebService = new FirebaseEventWebService(this);
  }


  public void fetchAllEvents() {
    this.eventWebService.fetchAllOrderedEvents();
  }

  @Override
  public void onDesctiptionFetched(String description) {

  }

  @Override
  public void onFetched(List<EventDataModel> data) {
    events.setValue(data);
  }

  @Override
  public void onFetchedFailed(String message) {

  }

  public void cleanup() {
    this.eventWebService.cleanWebServiceCallback();
  }

}
