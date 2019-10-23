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
public class FirebasePhotoWebservice implements PhotoWebService {
  private final static String PHOTO_PATH = "Images";
  private GenericWebserviceCallback<List<PhotoDataModel>> genericWebserviceCallback;

  private ValueEventListener valueEventListener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      List<PhotoDataModel> photosRetrieved = new ArrayList<>();

      for (DataSnapshot photo : dataSnapshot.getChildren()) {
        photosRetrieved.add(photo.getValue(PhotoDataModel.class));
      }

      if(genericWebserviceCallback != null) {
        genericWebserviceCallback.onFetched(photosRetrieved);
      }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
  };

  public FirebasePhotoWebservice(GenericWebserviceCallback<List<PhotoDataModel>> genericWebserviceCallback) {
    this.genericWebserviceCallback = genericWebserviceCallback;
  }



  @Override
  public void fetchAllPhotos() {
    FirebaseDatabase.getInstance().getReference(PHOTO_PATH).addValueEventListener(valueEventListener);
  }

  @Override
  public void cleanWebServiceCallback() {
    this.genericWebserviceCallback = null;
  }
}
