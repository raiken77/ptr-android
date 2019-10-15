package com.mru.ptr.district.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Recycler;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mru.ptr.DisabledToolbarFragment;
import com.mru.ptr.R;
import com.mru.ptr.district.ui.adapters.CandidateDetailAdapter;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.repository.DistrictViewModel;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class CandidateDetailFragment extends DisabledToolbarFragment implements AppBarLayout.OnOffsetChangedListener {

private AppCompatImageView candidateImage;
private AppCompatTextView candidateName;
private AppCompatTextView candidateParty;
private AppCompatTextView candidateDescription;
private AppCompatTextView toolbarTitle;
private Toolbar toolbar;
private AppBarLayout appBarLayout;
private DistrictViewModel viewModel;
private CollapsingToolbarLayout collapsingToolbarLayout;
  private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
  private int mMaxScrollSize;
  private boolean mIsImageHidden;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = ViewModelProviders.of(requireActivity()).get(DistrictViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_improved_candidate, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    appBarLayout = view.findViewById(R.id.app_bar);
    appBarLayout.addOnOffsetChangedListener(this);
    collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
    toolbar = view.findViewById(R.id.toolbar);
    toolbar.setNavigationIcon(R.drawable.ic_custom_back);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        getFragmentManager().popBackStack();
      }
    });
    candidateImage = view.findViewById(R.id.candidate_image);
    candidateDescription = view.findViewById(R.id.candidate_description);

    viewModel.getSelectedCandidate().observe(getViewLifecycleOwner(),
      new Observer<CandidateDataModel>() {
        @Override
        public void onChanged(CandidateDataModel dataModel) {
          if(dataModel != null) {
            updateUIWithFreshData(dataModel);
          }
        }
      });

  }

  private void updateUIWithFreshData(CandidateDataModel candidateDataModel) {
    collapsingToolbarLayout.setTitle(candidateDataModel.familyName);
    candidateDescription.setText(candidateDataModel.description);
  }

  @Override
  public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
    if (mMaxScrollSize == 0)
      mMaxScrollSize = appBarLayout.getTotalScrollRange();

    int currentScrollPercentage = (Math.abs(i)) * 100
      / mMaxScrollSize;

    if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
      if (!mIsImageHidden) {
        mIsImageHidden = true;

      }
    }

    if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
      if (mIsImageHidden) {
        mIsImageHidden = false;
      }
    }
  }
}
