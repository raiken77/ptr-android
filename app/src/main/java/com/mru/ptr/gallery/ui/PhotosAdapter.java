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
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosViewHolder> {

  List<PhotoDataModel> photoDataModels;
  private RecyclerViewClickListener clickListener;

  public PhotosAdapter(List<PhotoDataModel> photoDataModels, RecyclerViewClickListener clickListener) {
    this.photoDataModels = photoDataModels;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View inflatedView = inflater.inflate(R.layout.photo_row, parent, false);
    return new PhotosViewHolder(inflatedView, clickListener);
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

  public PhotoDataModel getItem(int position) {
    return this.photoDataModels.get(position);
  }

  public class PhotosViewHolder extends ViewHolder implements View.OnClickListener {

    AppCompatImageView image;

    WeakReference<RecyclerViewClickListener> clickListenerWeakReference;
    public PhotosViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      image = itemView.findViewById(R.id.photo);
      clickListenerWeakReference = new WeakReference<>(clickListener);
      itemView.setOnClickListener(this);
    }

    public void bindData(PhotoDataModel dataModel) {
      Glide.with(itemView.getContext())
        .load(dataModel.pictureUrl)
        .into(image);
    }

    @Override
    public void onClick(View view) {
      if(clickListenerWeakReference.get() != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
        clickListenerWeakReference.get().onItemSelected(view, getAdapterPosition());
      }
    }
  }

}
