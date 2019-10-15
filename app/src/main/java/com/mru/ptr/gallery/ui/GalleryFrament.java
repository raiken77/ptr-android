package com.mru.ptr.gallery.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.mru.ptr.DisabledToolbarFragment;
import com.mru.ptr.R;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class GalleryFrament extends DisabledToolbarFragment {

  GalleryPagerAdapter galleryPagerAdapter;
  ViewPager viewPager;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.gallery_fragment, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    galleryPagerAdapter = new GalleryPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    viewPager = view.findViewById(R.id.gallery_pager);
    viewPager.setAdapter(galleryPagerAdapter);
  }
}
