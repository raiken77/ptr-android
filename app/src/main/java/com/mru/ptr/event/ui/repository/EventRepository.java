package com.mru.ptr.event.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.event.ui.repository.disk.EventDao;
import com.mru.ptr.event.ui.repository.webservice.EventWebService;
import com.mru.ptr.event.ui.repository.webservice.EventMockWebService;
import com.mru.ptr.event.ui.repository.webservice.EventWebServiceCallback;
import com.mru.ptr.executor.PTRExecutor;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-14.
 */
public class EventRepository implements EventWebServiceCallback {

  private EventWebService eventWebService;
  private EventDao eventDao;
  public MutableLiveData<List<EventDataModel>> events = new MutableLiveData<>();

  public EventRepository() {
    eventWebService = new EventMockWebService(this);
  }


  public void fetchAllEvents() {
    this.eventWebService.fetchAllOrderedEvents();
  }

  @Override
  public void onFetched(final List<EventDataModel> data) {
    //Save data here
    events.setValue(data);
  }

  @Override
  public void onFetchedFailed(String message) {
    // Return a bad result here

  }

  @Override
  public void onDesctiptionFetched(String description) {

  }

  public void cleanup() {
    this.eventWebService.cleanWebServiceCallback();
  }




}
