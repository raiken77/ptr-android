package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mru.ptr.Response;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class VideoViewModel extends ViewModel {

  VideoRepository repository;
  LiveData<List<VideoDataModel>> videoData;
  private final MutableLiveData<VideoDataModel> selectedVideo;


  public VideoViewModel() {
    this.repository = new VideoRepository();
    this.videoData = new MutableLiveData<>();
    this.selectedVideo = new MutableLiveData<>();
  }


  public LiveData<Response<List<VideoDataModel>>> getAllVideos() {
    return this.repository.fetchAllVideos();
  }

  public void selectVideo(VideoDataModel videoDataModel) {
    this.selectedVideo.setValue(videoDataModel);
  }

  public LiveData<VideoDataModel> getSelectedVideo() {
    return selectedVideo;
  }

}
