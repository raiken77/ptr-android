package com.mru.ptr.event.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mru.ptr.Response;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.event.ui.repository.EventRepository;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class EventsViewModel extends ViewModel {
  private LiveData<List<EventDataModel>> viewModelData;
  private final MutableLiveData<EventDataModel> selectedDataModel;

  private EventRepository eventRepository;

  public EventsViewModel() {
    viewModelData = new MutableLiveData<>();
    selectedDataModel = new MutableLiveData<>();
    eventRepository = new EventRepository();
  }


  public LiveData<Response<List<EventDataModel>>> fetchAllEvents() {
    return eventRepository.getEvents();
  }

  public void selectEvent(EventDataModel event) {
    selectedDataModel.setValue(event);
  }

  public LiveData<EventDataModel> getSelected() {
    return selectedDataModel;
  }
}
