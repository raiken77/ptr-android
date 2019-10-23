package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mru.ptr.FirebaseQueryLiveData;
import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.Response;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class PhotoRepository implements GenericWebserviceCallback<List<PhotoDataModel>> {

  private PhotoWebService photoWebService;

  public MutableLiveData<List<PhotoDataModel>> photosRetrieved = new MutableLiveData<>();

  public PhotoRepository() {
    photoWebService = new FirebasePhotoWebservice(this);
  }


  public void fetchAllPhotos() {
     photoWebService.fetchAllPhotos();
  }

  @Override
  public void onFetched(List<PhotoDataModel> data) {
    photosRetrieved.setValue(data);
  }

  @Override
  public void onFetchedFailed(String message) {

  }
  public void clean() {
    photoWebService.cleanWebServiceCallback();
  }
}
