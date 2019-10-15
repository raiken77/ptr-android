package com.mru.ptr.gallery.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.mru.ptr.MainActivity;
import com.mru.ptr.R;
import com.mru.ptr.Response;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class VideosFragment extends Fragment implements RecyclerViewClickListener {

  RecyclerView videoRecyclerView;
  VideosAdapter videosAdapter;
  List<VideoDataModel> videos;
  VideoViewModel viewModel;
  private ProgressBar progressBar;
  private AppCompatTextView errorText;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    videos = new ArrayList<>();
    videos.add(new VideoDataModel().setTitle("Video 1").setDrawableRes(R.drawable.membership_benefits).setVideoUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/alliance%20nationale%20video.mp4?alt=media&token=0aaba94b-47ad-446e-97cc-74019e091f53"));
    videos.add(new VideoDataModel().setTitle("Video 2").setDrawableRes(R.drawable.membership_benefits).setVideoUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/alliance%20nationale%20video.mp4?alt=media&token=0aaba94b-47ad-446e-97cc-74019e091f53"));
    videos.add(new VideoDataModel().setTitle("Video 3").setDrawableRes(R.drawable.membership_benefits).setVideoUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/alliance%20nationale%20video.mp4?alt=media&token=0aaba94b-47ad-446e-97cc-74019e091f53"));
    videos.add(new VideoDataModel().setTitle("Video 4").setDrawableRes(R.drawable.membership_benefits).setVideoUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/alliance%20nationale%20video.mp4?alt=media&token=0aaba94b-47ad-446e-97cc-74019e091f53"));
    videos.add(new VideoDataModel().setTitle("Video 5").setDrawableRes(R.drawable.membership_benefits).setVideoUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/alliance%20nationale%20video.mp4?alt=media&token=0aaba94b-47ad-446e-97cc-74019e091f53"));
    videos.add(new VideoDataModel().setTitle("Video 6").setDrawableRes(R.drawable.membership_benefits).setVideoUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/alliance%20nationale%20video.mp4?alt=media&token=0aaba94b-47ad-446e-97cc-74019e091f53"));
    viewModel = ViewModelProviders.of(requireActivity()).get(VideoViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_gallery_video_tab, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    videoRecyclerView = view.findViewById(R.id.videos_list);
    progressBar = view.findViewById(R.id.loading_progress);
    errorText = view.findViewById(R.id.error_message_text);
    progressBar.setVisibility(View.VISIBLE);
    videosAdapter = new VideosAdapter(videos, this);
    LayoutManager manager = new LinearLayoutManager(getContext());

    videoRecyclerView.setLayoutManager(manager);
    videoRecyclerView.setAdapter(videosAdapter);

    viewModel.getAllVideos().observe(getViewLifecycleOwner(),
      new Observer<Response<List<VideoDataModel>>>() {
        @Override
        public void onChanged(Response<List<VideoDataModel>> listResponse) {
          progressBar.setVisibility(View.GONE);
          if(TextUtils.isEmpty(listResponse.errorMessage)) {
            videosAdapter.setData(listResponse.data);
          }
          else {
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("No Videos");
          }
        }
      });
  }

  @Override
  public void onItemSelected(View view, int position) {
    if(getActivity() != null && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
      viewModel.selectVideo(videosAdapter.getVideoDataModel(position));
      ((MainActivity) getActivity()).playVideo(videosAdapter.getVideoDataModel(position).videoUrl);
    }
  }
}
