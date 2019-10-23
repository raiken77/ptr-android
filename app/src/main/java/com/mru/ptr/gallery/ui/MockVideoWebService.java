package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
public class MockVideoWebService implements VideoWebService {
private GenericWebserviceCallback<List<VideoDataModel>> videoCallback;

  public MockVideoWebService(
    GenericWebserviceCallback<List<VideoDataModel>> videoCallback) {
    this.videoCallback = videoCallback;
  }

  @Override
  public void fetchAllVideoData() {

    List<VideoDataModel> videoDataModels = new ArrayList<>();
    videoDataModels.add(new VideoDataModel().setTitle("Some title").setVideoUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/alliance%20nationale%20video.mp4?alt=media&token=0aaba94b-47ad-446e-97cc-74019e091f53"));

    if(videoCallback != null) {
      videoCallback.onFetched(videoDataModels);
    }
  }

  @Override
  public void cleanWebServiceCallback() {
    this.videoCallback = null;
  }
}
