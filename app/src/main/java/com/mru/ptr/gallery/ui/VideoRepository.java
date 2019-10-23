package com.mru.ptr.gallery.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class VideoRepository implements GenericWebserviceCallback<List<VideoDataModel>> {

  VideoWebService videoWebService;
  public MutableLiveData<List<VideoDataModel>> videos = new MutableLiveData<>();

  public VideoRepository() {
    this.videoWebService = new FirebaseVideoWebService(this);
  }



  public void fetchAllVideos() {
    this.videoWebService.fetchAllVideoData();
  }

  @Override
  public void onFetched(List<VideoDataModel> data) {
    videos.setValue(data);
  }

  @Override
  public void onFetchedFailed(String message) {

  }
}
