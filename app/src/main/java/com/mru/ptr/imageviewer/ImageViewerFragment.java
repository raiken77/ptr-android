package com.mru.ptr.imageviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.Glide;
import com.mru.ptr.R;
import com.mru.ptr.gallery.ui.PhotoDataModel;
import com.mru.ptr.gallery.ui.PhotoViewModel;

/**
 * Created by Jonathan on 2019-10-23.
 */
public class ImageViewerFragment extends Fragment {

  private AppCompatTextView description;
  private AppCompatImageView image;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_image_viewer, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    description = view.findViewById(R.id.image_description);
    image = view.findViewById(R.id.chosen_image);

    AppCompatImageButton backButton = view.findViewById(R.id.back_button);
    backButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        getFragmentManager().popBackStack();
      }
    });

    PhotoViewModel photoViewModel = ViewModelProviders.of(getActivity()).get(PhotoViewModel.class);

    photoViewModel.getSelectedDataModel().observe(getViewLifecycleOwner(),
      new Observer<PhotoDataModel>() {
        @Override
        public void onChanged(PhotoDataModel photoDataModel) {
          description.setText(photoDataModel.description);
          Glide.with(getContext())
            .load(photoDataModel.pictureUrl)
            .into(image);
        }
      });

  }
}
