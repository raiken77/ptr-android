package com.mru.ptr.videoplayer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.mru.ptr.DisabledToolbarFragment;
import com.mru.ptr.R;
import com.mru.ptr.gallery.ui.VideoDataModel;
import com.mru.ptr.gallery.ui.VideoViewModel;

/**
 * Created by Jonathan on 2019-10-17.
 */
public class VideoPlayerFragment extends Fragment {

  private String videoUrl;
  private VideoViewModel viewModel;

  public static VideoPlayerFragment newInstance(String videoUrl) {

    Bundle args = new Bundle();
    args.putString("url", videoUrl);
    VideoPlayerFragment fragment = new VideoPlayerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private PlayerView playerView;
  private SimpleExoPlayer player;
  private Context context;
  private AppCompatImageButton backButton;

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = ViewModelProviders.of(requireActivity()).get(VideoViewModel.class);
    if(getArguments() != null) {
      videoUrl = getArguments().getString("url");
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_video_view, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    viewModel.getSelectedVideo().observe(getViewLifecycleOwner(), new Observer<VideoDataModel>() {
      @Override
      public void onChanged(VideoDataModel videoDataModel) {
        videoUrl = videoDataModel.videoUrl;
      }
    });
    playerView = view.findViewById(R.id.current_video);
    backButton = view.findViewById(R.id.back_button);

    backButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        getFragmentManager().popBackStack();
      }
    });
  }


  @Override
  public void onStart() {
    super.onStart();
    initialisePlayer(this.context);
    playerView.setPlayer(player);

    DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this.context,
      Util.getUserAgent(this.context, getString(R.string.app_name)));

    MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
      .setExtractorsFactory(new DefaultExtractorsFactory())
      .createMediaSource(
      Uri.parse(this.videoUrl));

    player.prepare(mediaSource);

    player.setPlayWhenReady(true);

  }

  @Override
  public void onStop() {
    super.onStop();
    playerView.setPlayer(null);
    player.release();
    player = null;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.context = null;
  }

  private void initialisePlayer(Context context) {
    TrackSelector trackSelector = new DefaultTrackSelector();
    LoadControl loadControl = new DefaultLoadControl();
    RenderersFactory renderersFactory = new DefaultRenderersFactory(context);

    player = ExoPlayerFactory.newSimpleInstance(context, renderersFactory, trackSelector, loadControl);
  }
}
