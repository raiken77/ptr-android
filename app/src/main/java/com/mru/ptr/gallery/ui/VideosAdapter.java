package com.mru.ptr.gallery.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bumptech.glide.Glide;
import com.mru.ptr.R;
import com.mru.ptr.gallery.ui.VideosAdapter.VideosViewHoder;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class VideosAdapter extends RecyclerView.Adapter<VideosViewHoder> {

  List<VideoDataModel> videoData;
  RecyclerViewClickListener clickListener;

  public VideosAdapter(List<VideoDataModel> videoData, RecyclerViewClickListener clickListener) {
    this.videoData = videoData;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public VideosViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.video_row, parent, false);
    return new VideosViewHoder(view, clickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull VideosViewHoder holder, int position) {
    VideoDataModel model = videoData.get(position);

    if(model != null) {
    holder.bindData(model);
    }
  }

  public void setData(List<VideoDataModel> videos) {
    this.videoData = videos;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return videoData.size();
  }

  public VideoDataModel getVideoDataModel(int position) {
    return videoData.get(position);
  }

  public class VideosViewHoder extends ViewHolder implements View.OnClickListener {

    AppCompatTextView titleView;
    AppCompatImageView imageView;
    private WeakReference<RecyclerViewClickListener> clickListenerWeakReference;

    public VideosViewHoder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      titleView = itemView.findViewById(R.id.video_title);
      imageView = itemView.findViewById(R.id.video_thumbnail);
      clickListenerWeakReference = new WeakReference<>(clickListener);
      itemView.setOnClickListener(this);
    }

    public void bindData(VideoDataModel dataModel) {
      titleView.setText(dataModel.title);
      Glide.with(itemView.getContext())
        .load(dataModel.thumbnailUrl)
        .into(imageView);

      imageView.setBackgroundResource(R.drawable.membership_benefits);
//      subtitleView.setText(dataModel.);
    }

    @Override
    public void onClick(View view) {
      if(clickListenerWeakReference.get() != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
        clickListenerWeakReference.get().onItemSelected(view, getAdapterPosition());
      }
    }
  }



}
