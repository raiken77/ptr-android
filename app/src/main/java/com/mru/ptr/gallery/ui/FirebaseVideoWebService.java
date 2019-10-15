package com.mru.ptr.gallery.ui;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class FirebaseVideoWebService implements VideoWebService {

  private final static String PATH = "Videos/others";

  public FirebaseVideoWebService() {
  }




  @Override
  public LiveData<Response<List<VideoDataModel>>> fetchAllVideoData() {
    final MutableLiveData<Response<List<VideoDataModel>>> videoLiveData = new MutableLiveData<>();

    FirebaseDatabase.getInstance().getReference(PATH).addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          if(dataSnapshot.exists()) {
            List<VideoDataModel> videoDataModels = new ArrayList<>();
            Response<List<VideoDataModel>> responseData = new Response<>();
            for(DataSnapshot videoData : dataSnapshot.getChildren()) {
              videoDataModels.add(videoData.getValue(VideoDataModel.class));
            }

            responseData
              .setStatus(ResponseStatus.SUCCESS)
              .setErrorMessage(null)
              .setData(videoDataModels);

            videoLiveData.postValue(responseData);
          }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
      });

    return videoLiveData;
  }
}
