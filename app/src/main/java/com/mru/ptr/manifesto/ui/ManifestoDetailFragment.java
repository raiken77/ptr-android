package com.mru.ptr.manifesto.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.mru.ptr.BackEnabledToolbarFragment;
import com.mru.ptr.R;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class ManifestoDetailFragment extends BackEnabledToolbarFragment {

  private AppCompatTextView title;
  private AppCompatTextView description;
  private AppCompatImageView image;
  private ManifestoViewModel manifestoViewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    manifestoViewModel = ViewModelProviders.of(requireActivity()).get(ManifestoViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_manifesto_detail, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    title = view.findViewById(R.id.manifesto_title);
    description = view.findViewById(R.id.manifesto_description);
    image = view.findViewById(R.id.manifesto_image);

    manifestoViewModel.getSelectedManifesto().observe(getViewLifecycleOwner(),
      new Observer<ManifestoDataModel>() {
        @Override
        public void onChanged(ManifestoDataModel manifestoDataModel) {
          updateUI(manifestoDataModel);
        }
      });
  }


  private void updateUI(ManifestoDataModel manifestoDataModel) {
    title.setText(manifestoDataModel.title);
    description.setText(manifestoDataModel.description);
  }
  @Override
  public String getTitle() {
    return "Manifesto Title";
  }
}
