package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-22.
 */
public class MockPhotoWebService implements PhotoWebService {

  private GenericWebserviceCallback<List<PhotoDataModel>> photos;

  public MockPhotoWebService(
    GenericWebserviceCallback<List<PhotoDataModel>> photos) {
    this.photos = photos;
  }

  @Override
  public void fetchAllPhotos() {
    final MutableLiveData<Response<List<PhotoDataModel>>> photosLiveData = new MutableLiveData<>();

    List<PhotoDataModel> photos = new ArrayList<>();
    photos.add(new PhotoDataModel().setPictureUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    photos.add(new PhotoDataModel().setPictureUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    photos.add(new PhotoDataModel().setPictureUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    photos.add(new PhotoDataModel().setPictureUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    photos.add(new PhotoDataModel().setPictureUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    photos.add(new PhotoDataModel().setPictureUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    photos.add(new PhotoDataModel().setPictureUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));

    this.photos.onFetched(photos);
  }

  @Override
  public void cleanWebServiceCallback() {
    this.photos = null;
  }
}
