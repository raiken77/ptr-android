package com.mru.ptr.district.ui;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.mru.ptr.BackDisabledToolbarFragment;
import com.mru.ptr.MainActivity;
import com.mru.ptr.MarginItemDecoration;
import com.mru.ptr.R;
import com.mru.ptr.Response;
import com.mru.ptr.district.ui.adapters.DistrictAdapter;
import com.mru.ptr.district.ui.adapters.DistrictAdapter.DistrictViewHolder;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import com.mru.ptr.district.ui.repository.DistrictViewModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class DistrictsFragment extends BackDisabledToolbarFragment implements RecyclerViewClickListener {

  private RecyclerView districtRecyclerView;
  private LinearLayoutManager recyclerLayoutManager;
  private DistrictAdapter districtAdapter;
  private List<DistrictDataModel> districtDataModels;
  private DistrictViewModel districtViewModel;
  private ProgressBar progressBar;
  private AppCompatTextView errorText;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    districtDataModels = new ArrayList<>();
    districtViewModel = ViewModelProviders.of(requireActivity()).get(DistrictViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_listview, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    districtRecyclerView = view.findViewById(R.id.list);
    progressBar = view.findViewById(R.id.loading_progress);
    errorText = view.findViewById(R.id.error_message_text);
    progressBar.setVisibility(View.VISIBLE);
    recyclerLayoutManager = new LinearLayoutManager(getContext());
    ItemDecoration marginDecorator = new MarginItemDecoration(5, 5, 0, 0);
    districtRecyclerView.addItemDecoration(marginDecorator);
    districtAdapter = new DistrictAdapter(districtDataModels, this);

    districtRecyclerView.setLayoutManager(recyclerLayoutManager);
    districtRecyclerView.setAdapter(districtAdapter);

    districtViewModel.fetchAllDistricts().observe(getViewLifecycleOwner(),
      new Observer<List<DistrictDataModel>>() {
        @Override
        public void onChanged(List<DistrictDataModel> districtDataModels) {
            progressBar.setVisibility(View.GONE);
          if(districtDataModels == null) {
            if(districtAdapter.getItemCount() == 0) {
              errorText.setText("No districts available");
            }
          }
          else {
            districtAdapter.setData(districtDataModels);
          }
        }
      });

  }

  @Override
  public void onItemSelected(View view, int position) {
    if(getActivity() != null && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
      districtViewModel.selectDistrict(districtAdapter.getModelAt(position));
      ((MainActivity) getActivity()).showCandidatesForDistrictPage();
    }
  }

  @Override
  public String getTitle() {
    return "Districts";
  }
}
