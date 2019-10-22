package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import com.mru.ptr.Response;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class PhotoRepository {

  private PhotoWebService photoWebService;

  public PhotoRepository() {
    this.photoWebService = new MockPhotoWebService();
  }


  public LiveData<Response<List<PhotoDataModel>>> fetchAllPhotos() {
    return photoWebService.fetchAllPhotos();
  }
}
