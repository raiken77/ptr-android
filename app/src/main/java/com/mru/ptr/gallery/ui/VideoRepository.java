package com.mru.ptr.gallery.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mru.ptr.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class VideoRepository {

  VideoWebService videoWebService;

  public VideoRepository() {
    this.videoWebService = new MockVideoWebService();
  }



  public LiveData<Response<List<VideoDataModel>>> fetchAllVideos() {
    return this.videoWebService.fetchAllVideoData();
  }
}
