package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mru.ptr.Response;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class PhotoViewModel extends ViewModel {
  private LiveData<List<PhotoDataModel>> viewModelData;
  private PhotoRepository photoRepository;

  public PhotoViewModel() {
    viewModelData = new MutableLiveData<>();
    photoRepository = new PhotoRepository();
  }


  public LiveData<Response<List<PhotoDataModel>>> getAllPhotos() {
    return photoRepository.fetchAllPhotos();
  }
}
