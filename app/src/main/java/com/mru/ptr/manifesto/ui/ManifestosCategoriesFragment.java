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
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import com.mru.ptr.BackDisabledToolbarFragment;
import com.mru.ptr.MainActivity;
import com.mru.ptr.MarginItemDecoration;
import com.mru.ptr.R;
import com.mru.ptr.Response;
import com.mru.ptr.manifesto.ui.adapters.ManifestoCategoryAdapter;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class ManifestosCategoriesFragment extends BackDisabledToolbarFragment implements RecyclerViewClickListener {
  private RecyclerView manifestoRecyclerView;
  private LinearLayoutManager recyclerLayoutManager;
  private ManifestoCategoryAdapter manifestoAdapter;
  private List<ManifestoCategoryDataModel> manifestoRows;
  private ManifestoViewModel manifestoViewModel;
  private ProgressBar progressBar;
  private AppCompatTextView errorText;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    manifestoRows = new ArrayList<>();
    manifestoViewModel = ViewModelProviders.of(requireActivity()).get(ManifestoViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_listview, container, false);
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    manifestoRecyclerView = view.findViewById(R.id.list);
    progressBar = view.findViewById(R.id.loading_progress);
    errorText = view.findViewById(R.id.error_message_text);
    progressBar.setVisibility(View.VISIBLE);
    recyclerLayoutManager  = new LinearLayoutManager(getContext());
    manifestoAdapter = new ManifestoCategoryAdapter(manifestoRows, this);
    ItemDecoration marginDecorator = new MarginItemDecoration(5, 5, 0, 0);
    manifestoRecyclerView.addItemDecoration(marginDecorator);
    manifestoRecyclerView.setLayoutManager(recyclerLayoutManager);
    manifestoRecyclerView.setAdapter(manifestoAdapter);

    manifestoViewModel.manifestoCategories.observe(getViewLifecycleOwner(),
      new Observer<List<ManifestoCategoryDataModel>>() {
        @Override
        public void onChanged(List<ManifestoCategoryDataModel> manifestoCategoryDataModels) {
          progressBar.setVisibility(View.GONE);
          if(manifestoCategoryDataModels == null) {
            if(manifestoAdapter.getItemCount() == 0) {
              errorText.setText("No districts available");
            }
          }
          else {
            manifestoAdapter.setData(manifestoCategoryDataModels);
          }
        }
      });
  }

  @Override
  public void onItemSelected(View view, int position) {
    if(getActivity() != null && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
      manifestoViewModel.selectCategory(manifestoAdapter.getCategoryByPosition(position));
      ((MainActivity) getActivity()).showManifestos();
    }
  }

  @Override
  public String getTitle() {
    return "Manifestos";
  }
}
