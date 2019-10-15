package com.mru.ptr.manifesto.ui.adapters;

import android.text.TextUtils;
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
import com.mru.ptr.manifesto.ui.adapters.ManifestoCategoryAdapter.ManifestoCategoryViewHolder;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class ManifestoCategoryAdapter extends RecyclerView.Adapter<ManifestoCategoryViewHolder> {

  private List<ManifestoCategoryDataModel> manifestoCategoryDataModels;
  private RecyclerViewClickListener clickListener;

  public ManifestoCategoryAdapter(List<ManifestoCategoryDataModel> manifestoCategoryDataModels, RecyclerViewClickListener clickListener) {
    this.manifestoCategoryDataModels = manifestoCategoryDataModels;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public ManifestoCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.manifesto_category_row, parent, false);

    return new ManifestoCategoryViewHolder(view, this.clickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull ManifestoCategoryViewHolder holder, int position) {
    ManifestoCategoryDataModel data = manifestoCategoryDataModels.get(position);

    if(data != null) {
      holder.bindData(data);
    }
  }

  public void setData(List<ManifestoCategoryDataModel> categoryDataModels) {
    this.manifestoCategoryDataModels = categoryDataModels;
    notifyDataSetChanged();
  }

  public ManifestoCategoryDataModel getCategoryByPosition(int position) {
    return this.manifestoCategoryDataModels.get(position);
  }

  @Override
  public int getItemCount() {
    return manifestoCategoryDataModels.size();
  }

  public class ManifestoCategoryViewHolder extends ViewHolder implements View.OnClickListener {

    private AppCompatImageView manifestoCategoryImage;
    private AppCompatTextView manifestoCategoryText;
    private WeakReference<RecyclerViewClickListener> clickListenerWeakReference;

    public ManifestoCategoryViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      clickListenerWeakReference = new WeakReference<>(clickListener);
      manifestoCategoryImage = itemView.findViewById(R.id.manifesto_image);
      manifestoCategoryText = itemView.findViewById(R.id.manifesto_category_title);
      itemView.setOnClickListener(this);

    }

    public void bindData(ManifestoCategoryDataModel dataModel) {
      manifestoCategoryText.setText(dataModel.name);
      if(!TextUtils.isEmpty(dataModel.imageUrl)) {
        Glide.with(itemView.getContext())
          .load(dataModel.imageUrl)
          .into(manifestoCategoryImage);
      }

      manifestoCategoryImage.setBackgroundResource(dataModel.drawableRes);
    }

    @Override
    public void onClick(View view) {
      if(clickListenerWeakReference.get() != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
        clickListenerWeakReference.get().onItemSelected(view,getAdapterPosition());
      }
    }
  }

}
