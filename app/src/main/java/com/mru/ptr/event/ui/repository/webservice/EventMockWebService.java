package com.mru.ptr.event.ui.repository.webservice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import com.mru.ptr.event.ui.model.EventDataModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-20.
 */
public class EventMockWebService implements EventWebService {

  @Override
  public LiveData<Response<List<EventDataModel>>> fetchAllOrderedEvents() {
    final MutableLiveData<Response<List<EventDataModel>>> events = new MutableLiveData<>();
    events.setValue(createMockEvents());
    return events;
  }


  private Response<List<EventDataModel>> createMockEvents() {
    List<EventDataModel> events = new ArrayList<>();
    events.add(new EventDataModel().setTitle("Event 1").setDescription("My event 0").setState("UPCOMING").setDateTime(1571948304000L));
    events.add(new EventDataModel().setTitle("Event 2").setDescription("My event 1").setState("UPCOMING").setDateTime(1572380304000L));
    events.add(new EventDataModel().setTitle("Event 3").setDescription("My event 2").setState("UPCOMING").setDateTime(1572898704000L));
    events.add(new EventDataModel().setTitle("Event 4").setDescription("My event 3").setState("UPCOMING").setDateTime( 1573330704000L));
    events.add(new EventDataModel().setTitle("Event 5").setDescription("My event 4").setState("UPCOMING").setDateTime(1573762704000L));
    events.add(new EventDataModel().setTitle("Event 6").setDescription("My event 5").setState("UPCOMING").setDateTime(1574194704000L));
    events.add(new EventDataModel().setTitle("Event 7").setDescription("My event 6").setState("UPCOMING").setDateTime(1574626704000L));

    return
      new Response<List<EventDataModel>>()
      .setStatus(ResponseStatus.SUCCESS)
      .setData(events)
      .setErrorMessage(null);
  }
}
