package com.mru.ptr.manifesto.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mru.ptr.MainActivity;
import com.mru.ptr.R;
import com.mru.ptr.manifesto.ui.adapters.ManifestoAdapter;
import com.mru.ptr.manifesto.ui.model.ManifestoRow;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class ManifestosFragment extends Fragment implements RecyclerViewClickListener {
  private RecyclerView manifestoRecyclerView;
  private LinearLayoutManager recyclerLayoutManager;
  private ManifestoAdapter manifestoAdapter;
  private List<ManifestoRow> manifestoRows;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    manifestoRows = new ArrayList<>();
    manifestoRows.add(new ManifestoRow().setManifestoTitle("Somethign"));
    manifestoRows.add(new ManifestoRow().setManifestoTitle("Great"));
    manifestoRows.add(new ManifestoRow().setManifestoTitle("Awesome"));
    manifestoRows.add(new ManifestoRow().setManifestoTitle("Haha"));
    manifestoRows.add(new ManifestoRow().setManifestoTitle("Hey"));
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_manifestos, container, false);
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    manifestoRecyclerView = view.findViewById(R.id.manifestos_list);
    recyclerLayoutManager  = new LinearLayoutManager(getContext());
    manifestoAdapter = new ManifestoAdapter(manifestoRows, this);

    manifestoRecyclerView.setLayoutManager(recyclerLayoutManager);
    manifestoRecyclerView.setAdapter(manifestoAdapter);
  }

  @Override
  public void onItemSelected(int position) {
    if(getActivity() != null && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
      ((MainActivity) getActivity()).showManifestoDetail();
    }
  }
}
