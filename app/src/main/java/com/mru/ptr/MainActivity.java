package com.mru.ptr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

  private FragmentManager fragmentManager;
  private FragmentTransaction fragmentTransaction;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    fragmentManager = getSupportFragmentManager();

    if(savedInstanceState == null) {
    }
  }


  private void switchFragment(Fragment fragment) {
    fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.current_fragment, fragment);
    fragmentTransaction.commit();
  }
}
