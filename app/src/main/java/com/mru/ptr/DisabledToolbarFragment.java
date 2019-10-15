package com.mru.ptr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * Created by Jonathan on 2019-10-17.
 */
public class DisabledToolbarFragment extends Fragment {


  @Override
  public void onResume() {
    super.onResume();
    if(getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
      ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if(getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
      ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
  }
}
