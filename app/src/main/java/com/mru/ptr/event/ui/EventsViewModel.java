package com.mru.ptr.event.ui;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.event.ui.repository.EventRepository;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class EventsViewModel extends ViewModel {

  private final MutableLiveData<EventDataModel> selectedDataModel;

  private EventRepository eventRepository;

  public EventsViewModel() {
    selectedDataModel = new MutableLiveData<>();
    eventRepository = new EventRepository();
  }


  public LiveData<List<EventDataModel>> fetchAllEvents() {
    eventRepository.fetchAllEvents();
    return eventRepository.events;
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
