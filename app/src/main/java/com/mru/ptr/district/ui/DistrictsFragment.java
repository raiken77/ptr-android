package com.mru.ptr.district.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mru.ptr.MainActivity;
import com.mru.ptr.R;
import com.mru.ptr.district.ui.adapters.DistrictAdapter;
import com.mru.ptr.district.ui.model.DistrictRow;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class DistrictsFragment extends Fragment implements RecyclerViewClickListener {
  private RecyclerView districtRecyclerView;
  private LinearLayoutManager recyclerLayoutManager;
  private DistrictAdapter districtAdapter;
  private List<DistrictRow> districtRows;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    districtRows = new ArrayList<>();
    districtRows.add(new DistrictRow().setDistrictNumber("CLP21"));
    districtRows.add(new DistrictRow().setDistrictNumber("CLP20"));
    districtRows.add(new DistrictRow().setDistrictNumber("CLP19"));
    districtRows.add(new DistrictRow().setDistrictNumber("CLP30"));
    districtRows.add(new DistrictRow().setDistrictNumber("CLP32"));
    districtRows.add(new DistrictRow().setDistrictNumber("CLP22"));
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_districts, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    districtRecyclerView = view.findViewById(R.id.districts_list);
    recyclerLayoutManager = new LinearLayoutManager(getContext());
    districtAdapter = new DistrictAdapter(districtRows, this);

    districtRecyclerView.setLayoutManager(recyclerLayoutManager);
    districtRecyclerView.setAdapter(districtAdapter);
  }

  @Override
  public void onItemSelected(int position) {
    if(getActivity() != null && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
      ((MainActivity) getActivity()).showCandidateDetailPage();
    }
  }
}
