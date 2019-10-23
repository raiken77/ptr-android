package com.mru.ptr.event.ui.repository.webservice;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mru.ptr.event.ui.model.EventDataModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-14.
 */
public class FirebaseEventWebService implements EventWebService{
  private static final String BASE_DATABASE_REF = "Events";
  private DatabaseReference dbRef;
  private EventWebServiceCallback callback;

  private ValueEventListener valueEventListener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      List<EventDataModel> eventsRetrieved = new ArrayList<>();

      for(DataSnapshot event : dataSnapshot.getChildren()) {
        eventsRetrieved.add(event.getValue(EventDataModel.class));
      }

      if(callback != null) {
        callback.onFetched(eventsRetrieved);
      }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
  };


  public FirebaseEventWebService(EventWebServiceCallback callback) {
    dbRef = FirebaseDatabase.getInstance().getReference(BASE_DATABASE_REF);
    this.callback = callback;
  }


  @Override
  public void fetchAllOrderedEvents() {
    dbRef.addValueEventListener(valueEventListener);
  }

  @Override
  public void cleanWebServiceCallback() {
    this.callback = null;
  }


}
