package com.mru.ptr.event.ui;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.google.firebase.database.DataSnapshot;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import com.mru.ptr.SingleLiveEvent;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.event.ui.repository.EventRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class EventsViewModel extends ViewModel {

  private final SingleLiveEvent<EventDataModel> selectedDataModel;

  public LiveData<List<EventDataModel>> events;

  private EventRepository eventRepository;

  public EventsViewModel() {
    selectedDataModel = new SingleLiveEvent<>();
    eventRepository = new EventRepository();
    events = eventRepository.events;
    eventRepository.fetchAllEvents();
  }



  public void selectEvent(EventDataModel event) {
    selectedDataModel.setValue(event);
  }

  public LiveData<EventDataModel> getSelected() {
    return selectedDataModel;
  }

  @Override
  protected void onCleared() {
    if(eventRepository != null) {
      eventRepository.cleanup();
    }
    super.onCleared();
  }
}
