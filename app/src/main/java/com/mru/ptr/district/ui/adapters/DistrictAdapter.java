package com.mru.ptr.district.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.mru.ptr.R;
import com.mru.ptr.district.ui.adapters.DistrictAdapter.DistrictViewHolder;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class DistrictAdapter extends RecyclerView.Adapter<DistrictViewHolder> {

  private List<DistrictDataModel> districtDataModels;
  private RecyclerViewClickListener clickListener;

  public DistrictAdapter(List<DistrictDataModel> districtDataModels,
    RecyclerViewClickListener clickListener) {
    this.districtDataModels = districtDataModels;
    this.clickListener = clickListener;
  }



  @NonNull
  @Override
  public DistrictViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.district_row, parent, false);

    return new DistrictViewHolder(view, this.clickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull DistrictViewHolder holder, int position) {
    DistrictDataModel row = districtDataModels.get(position);

    if(row != null) {
      holder.bindData(row);
    }
  }

  public void setData(List<DistrictDataModel> districts) {
    this.districtDataModels = districts;
    notifyDataSetChanged();
  }

  public DistrictDataModel getModelAt(int position) {
    return this.districtDataModels.get(position);
  }

  @Override
  public int getItemCount() {
    return districtDataModels.size();
  }

  public static class DistrictViewHolder extends ViewHolder implements View.OnClickListener {

    private WeakReference<RecyclerViewClickListener> weakClickListener;
    private AppCompatTextView districtNumberTextView;
    private AppCompatImageView candidatesBackgroundImage;

    public DistrictViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      weakClickListener = new WeakReference<>(clickListener);
      districtNumberTextView    = itemView.findViewById(R.id.district_number);
      candidatesBackgroundImage = itemView.findViewById(R.id.candidates_background);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      if(weakClickListener.get() != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
        weakClickListener.get().onItemSelected(view, getAdapterPosition());
      }
    }

    public void bindData(DistrictDataModel districtDataModel) {
      districtNumberTextView.setText(districtDataModel.name);
    }
  }
}
