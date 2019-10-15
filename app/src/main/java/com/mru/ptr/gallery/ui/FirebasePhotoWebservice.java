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
public class FirebasePhotoWebservice implements PhotoWebService {
  private final static String PHOTO_PATH = "Images";

  public FirebasePhotoWebservice() {
  }



  @Override
  public LiveData<Response<List<PhotoDataModel>>> fetchAllPhotos() {
    final MutableLiveData<Response<List<PhotoDataModel>>> photoLiveData = new MutableLiveData<>();

    FirebaseDatabase.getInstance().getReference(PHOTO_PATH).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
          List<PhotoDataModel> photosRetrieved = new ArrayList<>();
          Response<List<PhotoDataModel>> photosResponse = new Response<>();

          for (DataSnapshot photo : dataSnapshot.getChildren()) {
            photosRetrieved.add(photo.getValue(PhotoDataModel.class));
          }

          photosResponse
            .setData(photosRetrieved)
            .setErrorMessage(null)
            .setStatus(ResponseStatus.SUCCESS);

          photoLiveData.postValue(photosResponse);
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });

    return photoLiveData;
  }
}
