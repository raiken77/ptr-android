package com.mru.ptr.manifesto.ui;

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
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.mru.ptr.BackEnabledToolbarFragment;
import com.mru.ptr.MainActivity;
import com.mru.ptr.R;
import com.mru.ptr.Response;
import com.mru.ptr.manifesto.ui.adapters.ManifestoAdapter;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class ManifestosFragment extends BackEnabledToolbarFragment implements RecyclerViewClickListener {

  List<ManifestoDataModel> manifestoDataModels;
  RecyclerView manifestoRecyclerView;
  ManifestoAdapter adapter;
  private ManifestoViewModel manifestoViewModel;
  private ProgressBar progressBar;
  private AppCompatTextView errorText;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    manifestoDataModels = new ArrayList<>();
    manifestoViewModel = ViewModelProviders.of(requireActivity()).get(ManifestoViewModel.class);
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
    manifestoRecyclerView = view.findViewById(R.id.list);
    progressBar = view.findViewById(R.id.loading_progress);
    errorText = view.findViewById(R.id.error_message_text);
    progressBar.setVisibility(View.VISIBLE);
    adapter = new ManifestoAdapter(manifestoDataModels, this);
    LayoutManager verticalLayoutManager = new LinearLayoutManager(getContext());
    manifestoRecyclerView.setLayoutManager(verticalLayoutManager);
    manifestoRecyclerView.setAdapter(adapter);

    manifestoViewModel.getSelectedCategory().observe(getViewLifecycleOwner(),
      new Observer<ManifestoCategoryDataModel>() {
        @Override
        public void onChanged(ManifestoCategoryDataModel dataModel) {
          manifestoViewModel.getAllManifestosByCategory(dataModel.id).observe(
            getViewLifecycleOwner(), new Observer<Response<List<ManifestoDataModel>>>() {
              @Override
              public void onChanged(Response<List<ManifestoDataModel>> listResponse) {
                progressBar.setVisibility(View.GONE);
                if(TextUtils.isEmpty(listResponse.errorMessage)) {
                  adapter.setData(listResponse.data);
                }
                else {
                  errorText.setVisibility(View.VISIBLE);
                  errorText.setText("No Manifestos");
                }
              }
            });
        }
      });

  }

  @Override
  public void onItemSelected(View view, int position) {
    if(getActivity() != null && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
      manifestoViewModel.selectManifesto(adapter.getManifestoAtPosition(position));
      ((MainActivity) getActivity()).showManifestoDetail();
    }
  }

  @Override
  public String getTitle() {
    return "Manifesto";
  }
}
