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
import com.mru.ptr.district.ui.adapters.CandidateDetailAdapter.CandidateDetailViewHolder;
import com.mru.ptr.district.ui.model.CandidateDetailRow;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class CandidateDetailAdapter extends RecyclerView.Adapter<CandidateDetailViewHolder> {

  private List<CandidateDetailRow> candidateDetails;
  private RecyclerViewClickListener clickListener;

  public CandidateDetailAdapter(List<CandidateDetailRow> candidateDetails, RecyclerViewClickListener clickListener) {
    this.candidateDetails = candidateDetails;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public CandidateDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.candidate_info_row, parent, false);
    return new CandidateDetailViewHolder(view, this.clickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull CandidateDetailViewHolder holder, int position) {
    CandidateDetailRow row = candidateDetails.get(position);

    if(row != null) {
      holder.bindData(row);
    }
  }

  @Override
  public int getItemCount() {
    return candidateDetails.size();
  }

  public static class CandidateDetailViewHolder extends ViewHolder implements View.OnClickListener {

    private WeakReference<RecyclerViewClickListener> clickListenerWeakReference;
    private AppCompatImageView candidateImage;
    private AppCompatTextView candidateName;
    private AppCompatTextView candidateDescription;
    private AppCompatImageView candidateVideoThumbnail;

    public CandidateDetailViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      clickListenerWeakReference = new WeakReference<>(clickListener);
      candidateImage = itemView.findViewById(R.id.candidate_image);

      itemView.setOnClickListener(this);
    }

    public void bindData(CandidateDetailRow row) {

    }

    @Override
    public void onClick(View view) {
      if(clickListenerWeakReference.get() != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
        clickListenerWeakReference.get().onItemSelected(getAdapterPosition());
      }
    }
  }

}
