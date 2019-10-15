package com.mru.ptr.event.ui.repository;

import androidx.lifecycle.LiveData;
import com.mru.ptr.Response;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.event.ui.repository.webservice.EventWebService;
import com.mru.ptr.event.ui.repository.webservice.EventMockWebService;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-14.
 */
public class EventRepository {

  private EventWebService eventWebService;
  public LiveData<Response<List<EventDataModel>>> viewModelData;

  public EventRepository() {
    eventWebService = new EventMockWebService();
  }

  public LiveData<Response<List<EventDataModel>>> getEvents() {
    return eventWebService.fetchAllOrderedEvents();
  }

}
