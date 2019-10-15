package com.mru.ptr.gallery.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bumptech.glide.Glide;
import com.mru.ptr.R;
import com.mru.ptr.gallery.ui.PhotosAdapter.PhotosViewHolder;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosViewHolder> {

  List<PhotoDataModel> photoDataModels;

  public PhotosAdapter(List<PhotoDataModel> photoDataModels) {
    this.photoDataModels = photoDataModels;
  }

  @NonNull
  @Override
  public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View inflatedView = inflater.inflate(R.layout.photo_row, parent, false);
    return new PhotosViewHolder(inflatedView);
  }

  @Override
  public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
    PhotoDataModel row = photoDataModels.get(position);
    if(row != null) {
      holder.bindData(row);
    }
  }

  public void setData(List<PhotoDataModel> photos) {
    this.photoDataModels = photos;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return photoDataModels.size();
  }

  public class PhotosViewHolder extends ViewHolder {

    AppCompatImageView image;

    public PhotosViewHolder(@NonNull View itemView) {
      super(itemView);
      image = itemView.findViewById(R.id.photo);
    }

    public void bindData(PhotoDataModel dataModel) {
      Glide.with(itemView.getContext())
        .load(dataModel.pictureUrl)
        .into(image);
    }
  }

}
