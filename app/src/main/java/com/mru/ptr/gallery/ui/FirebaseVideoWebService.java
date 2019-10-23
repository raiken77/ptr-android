package com.mru.ptr.gallery.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class FirebaseVideoWebService implements VideoWebService {

  private final static String PATH = "Videos/others";
  private GenericWebserviceCallback<List<VideoDataModel>> callback;

  private ValueEventListener valueEventListener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      List<VideoDataModel> videoDataModels = new ArrayList<>();
      for(DataSnapshot videoData : dataSnapshot.getChildren()) {
        videoDataModels.add(videoData.getValue(VideoDataModel.class));
      }

      if(callback != null) {
        callback.onFetched(videoDataModels);
      }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
  };

  public FirebaseVideoWebService(
    GenericWebserviceCallback<List<VideoDataModel>> callback) {
    this.callback = callback;
  }

  @Override
  public void fetchAllVideoData() {
    FirebaseDatabase.getInstance().getReference(PATH).addValueEventListener(valueEventListener);
  }

  @Override
  public void cleanWebServiceCallback() {
    this.callback = null;
  }
}
