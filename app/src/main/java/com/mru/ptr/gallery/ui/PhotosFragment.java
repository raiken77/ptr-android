package com.mru.ptr.gallery.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.mru.ptr.MainActivity;
import com.mru.ptr.R;
import com.mru.ptr.Response;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class PhotosFragment extends Fragment implements RecyclerViewClickListener {

  RecyclerView photosRecyclerView;
  PhotosAdapter adapter;
  List<PhotoDataModel> photos;
  PhotoViewModel photoViewModel;
  private ProgressBar progressBar;
  private AppCompatTextView errorText;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    photos = new ArrayList<>();
    photoViewModel = ViewModelProviders.of(getActivity()).get(PhotoViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_listview, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    photosRecyclerView = view.findViewById(R.id.list);
    progressBar = view.findViewById(R.id.loading_progress);
    errorText = view.findViewById(R.id.error_message_text);
    progressBar.setVisibility(View.VISIBLE);
    adapter = new PhotosAdapter(photos, this);
    LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);

    photosRecyclerView.setLayoutManager(layoutManager);
    photosRecyclerView.setAdapter(adapter);

    photoViewModel.photoDataModel.observe(getViewLifecycleOwner(),
      new Observer<List<PhotoDataModel>>() {
        @Override
        public void onChanged(List<PhotoDataModel> photoDataModels) {
          progressBar.setVisibility(View.GONE);
          if(photoDataModels != null && !photoDataModels.isEmpty()) {
            adapter.setData(photoDataModels);
          }
          else {
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("No Images");
          }
        }
      });
  }

  @Override
  public void onItemSelected(View view, int position) {
    if(getActivity() != null && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
      photoViewModel.selectPhoto(adapter.getItem(position));
      ((MainActivity) getActivity()).showImage();
    }
  }
}
