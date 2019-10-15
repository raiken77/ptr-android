package com.mru.ptr.district.ui.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bumptech.glide.Glide;
import com.mru.ptr.R;
import com.mru.ptr.district.ui.adapters.CandidateDetailAdapter.CandidateDetailViewHolder;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class CandidateDetailAdapter extends RecyclerView.Adapter<CandidateDetailViewHolder> {

  private List<CandidateDataModel> candidateDetails;
  private RecyclerViewClickListener clickListener;

  public CandidateDetailAdapter(List<CandidateDataModel> candidateDetails, RecyclerViewClickListener clickListener) {
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
    CandidateDataModel row = candidateDetails.get(position);

    if(row != null) {
      holder.bindData(row);
      ViewCompat.setTransitionName(holder.candidateImage, "Row: " + new Random().nextInt(400));
    }
  }

  public void setData(List<CandidateDataModel> candidateDetails) {
    this.candidateDetails = candidateDetails;
    notifyDataSetChanged();
  }

  public CandidateDataModel getItemAtPosition(int position) {
    return this.candidateDetails.get(position);
  }

  @Override
  public int getItemCount() {
    return candidateDetails.size();
  }

  public static class CandidateDetailViewHolder extends ViewHolder implements View.OnClickListener {

    private WeakReference<RecyclerViewClickListener> clickListenerWeakReference;
    private AppCompatImageView candidateImage;
    private AppCompatTextView candidateName;
    private AppCompatTextView candidateParty;


    public CandidateDetailViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      clickListenerWeakReference = new WeakReference<>(clickListener);
      candidateImage = itemView.findViewById(R.id.candidate_image);
      candidateName = itemView.findViewById(R.id.candidate_full_name);
      candidateParty = itemView.findViewById(R.id.candidate_party);

      itemView.setOnClickListener(this);
      candidateImage.setOnClickListener(this);
    }

    public void bindData(CandidateDataModel row) {
      candidateName.setText(String.format(Locale.ENGLISH, "%s %s", row.name, row.familyName));
      candidateParty.setText(row.party);
      if(!TextUtils.isEmpty(row.imageUrl)) {
        Glide.with(itemView.getContext())
          .load(row.imageUrl)
          .into(candidateImage);
      }
      else {
        Glide.with(itemView.getContext())
          .clear(candidateImage);
      }
    }

    @Override
    public void onClick(View view) {
      if(clickListenerWeakReference.get() != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
        clickListenerWeakReference.get().onItemSelected(this.candidateImage, getAdapterPosition());
      }
    }
  }

}
