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
  public LiveData<List<PhotoDataModel>> photoDataModel;
  private PhotoRepository photoRepository;
  private MutableLiveData<PhotoDataModel> selectedPhoto = new MutableLiveData<>();

  public PhotoViewModel() {
    viewModelData = new MutableLiveData<>();
    photoRepository = new PhotoRepository();
    photoDataModel = photoRepository.photosRetrieved;
    photoRepository.fetchAllPhotos();
  }


  public void selectPhoto(PhotoDataModel photoDataModel) {
    selectedPhoto.setValue(photoDataModel);
  }

  public LiveData<PhotoDataModel> getSelectedDataModel() {
    return selectedPhoto;
  }

}
