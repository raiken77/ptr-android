package com.mru.ptr.district.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.mru.ptr.BackEnabledToolbarFragment;
import com.mru.ptr.MainActivity;
import com.mru.ptr.MarginItemDecoration;
import com.mru.ptr.R;
import com.mru.ptr.district.ui.adapters.CandidateDetailAdapter;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import com.mru.ptr.district.ui.repository.DistrictViewModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class CandidatesFragment extends BackEnabledToolbarFragment implements
  RecyclerViewClickListener {

  RecyclerView candidateDetailList;
  CandidateDetailAdapter candidateDetailAdapter;
  List<CandidateDataModel> candidateDataModels;
  private DistrictViewModel districtViewModel;
  private ProgressBar progressBar;
  private AppCompatTextView errorText;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_padded_listview, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    candidateDataModels = new ArrayList<>();
    candidateDetailList = view.findViewById(R.id.list);
    progressBar = view.findViewById(R.id.loading_progress);
    errorText = view.findViewById(R.id.error_message_text);
    progressBar.setVisibility(View.VISIBLE);
    candidateDetailAdapter = new CandidateDetailAdapter(candidateDataModels, this);
    ItemDecoration marginDecoration = new MarginItemDecoration(5, 5, 5, 5);
    LayoutManager verticalLayoutManager = new LinearLayoutManager(getContext());
    candidateDetailList.setLayoutManager(verticalLayoutManager);
    candidateDetailList.addItemDecoration(marginDecoration);
    candidateDetailList.setAdapter(candidateDetailAdapter);

    districtViewModel = ViewModelProviders.of(requireActivity()).get(DistrictViewModel.class);
    districtViewModel.getSelected().observe(getViewLifecycleOwner(),
      new Observer<DistrictDataModel>() {
        @Override
        public void onChanged(DistrictDataModel districtDataModel) {
          districtViewModel.fetchCandidatesByDistrict(districtDataModel.id).observe(
            getViewLifecycleOwner(), new Observer<List<CandidateDataModel>>() {
              @Override
              public void onChanged(List<CandidateDataModel> candidateDataModels) {
                progressBar.setVisibility(View.GONE);

                if(candidateDataModels == null) {
                  if (candidateDetailAdapter != null
                    && candidateDetailAdapter.getItemCount() == 0) {
                    errorText.setText("No candidates available");
                  }
                }
                else {
                  candidateDetailAdapter.setData(candidateDataModels);
                }
              }
            });
        }
      });


  }

  @Override
  public void onItemSelected(View view, int position) {
    if (getActivity() != null && getLifecycle().getCurrentState()
      .isAtLeast(Lifecycle.State.STARTED)) {
      districtViewModel.selectCandidate(candidateDetailAdapter.getItemAtPosition(position));
      ((MainActivity) getActivity()).showCandidateDetailPage(view);
    }
  }

  @Override
  public String getTitle() {
    return "Candidates";
  }
}
