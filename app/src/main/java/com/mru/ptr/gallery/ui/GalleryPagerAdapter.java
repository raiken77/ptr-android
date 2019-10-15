package com.mru.ptr.gallery.ui;

import android.provider.ContactsContract.Contacts.Photo;
import android.provider.MediaStore.Video;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class GalleryPagerAdapter extends FragmentPagerAdapter {

  public GalleryPagerAdapter(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    return getFragmentByPosition(position);
  }

  private @NonNull Fragment getFragmentByPosition(int position) {
    switch (position) {

      case 1:
        return new VideosFragment();

        default:
          return new PhotosFragment();
    }
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return getCurrentSelectedFragmentTitle(position);
  }

  private String getCurrentSelectedFragmentTitle(int position) {
    switch (position) {
      case 1:
        return "Videos";

      default:
        return "Photos";
    }
  }

  @Override
  public int getCount() {
    return 2;
  }
}
