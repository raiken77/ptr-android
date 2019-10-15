package com.mru.ptr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * Created by Jonathan on 2019-10-17.
 */
public abstract class BackDisabledToolbarFragment extends Fragment {

  public abstract String getTitle();

  public void setTitle(String title) {

  }

  @Override
  public void onResume() {
    super.onResume();
    if(getActivity() != null && ((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
      ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getTitle());
      ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

  }

  @Override
  public void onPause() {
    super.onPause();
    if(getActivity() != null && ((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
      ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
      ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");
    }
  }
}
