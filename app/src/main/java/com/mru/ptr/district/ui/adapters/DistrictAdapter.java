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
import com.mru.ptr.district.ui.model.DistrictRow;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class DistrictAdapter extends RecyclerView.Adapter<DistrictViewHolder> {

  private List<DistrictRow> districtRows;
  private RecyclerViewClickListener clickListener;

  public DistrictAdapter(List<DistrictRow> districtRows,
    RecyclerViewClickListener clickListener) {
    this.districtRows = districtRows;
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
    DistrictRow row = districtRows.get(position);

    if(row != null) {
      holder.bindData(row);
    }
  }

  @Override
  public int getItemCount() {
    return districtRows.size();
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
        weakClickListener.get().onItemSelected(getAdapterPosition());
      }
    }

    public void bindData(DistrictRow districtRow) {
      districtNumberTextView.setText(districtRow.districtNumber);
    }
  }
}
