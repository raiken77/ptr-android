package com.mru.ptr.event.ui.repository.webservice;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import com.mru.ptr.event.ui.model.EventDataModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-14.
 */
public class FirebaseEventWebService implements EventWebService{
  private static final String BASE_DATABASE_REF = "Events";
  private DatabaseReference dbRef;


  public FirebaseEventWebService() {
    dbRef = FirebaseDatabase.getInstance().getReference(BASE_DATABASE_REF);
  }


  @Override
  public LiveData<Response<List<EventDataModel>>> fetchAllOrderedEvents() {
    final MutableLiveData<Response<List<EventDataModel>>> events = new MutableLiveData<>();

    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<EventDataModel> eventsRetrieved = new ArrayList<>();
        Response<List<EventDataModel>> responseEvents = new Response<>();

        for(DataSnapshot event : dataSnapshot.getChildren()) {
          eventsRetrieved.add(event.getValue(EventDataModel.class));
        }

        responseEvents
          .setData(eventsRetrieved)
          .setErrorMessage(null)
          .setStatus(ResponseStatus.SUCCESS);

        events.postValue(responseEvents);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        //TODO Handle errors here
      }
    });
    return events;
  }
}
