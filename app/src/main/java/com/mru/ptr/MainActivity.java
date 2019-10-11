package com.mru.ptr;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.mru.ptr.district.ui.CandidateDetailFragment;
import com.mru.ptr.district.ui.DistrictsFragment;
import com.mru.ptr.event.ui.EventDetailFragment;
import com.mru.ptr.event.ui.EventsFragment;
import com.mru.ptr.manifesto.ui.ManifestoDetailFragment;
import com.mru.ptr.manifesto.ui.ManifestosFragment;
import com.mru.ptr.settings.ui.SettingsFragment;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

  private FragmentManager fragmentManager;
  private FragmentTransaction fragmentTransaction;
  private BottomNavigationView bottomNavigationView;
  private String currentMenuItemTag = "EVENTS_ROOT";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    fragmentManager = getSupportFragmentManager();

    bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    bottomNavigationView.setOnNavigationItemSelectedListener(this);

    if(savedInstanceState == null) {
      displayAppropriateFragment(R.id.bottom_navigation_item_events);
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    return displayAppropriateFragment(menuItem.getItemId());
  }

  private boolean displayAppropriateFragment(int menuItemId) {
    switch(menuItemId) {
      case R.id.bottom_navigation_item_events:
        switchFragmentWithTag(new EventsFragment(), "EVENTS_ROOT");
        return true;

      case R.id.bottom_navigation_item_gallery:
        return true;

      case R.id.bottom_navigation_item_manifesto:
        switchFragmentWithTag(new ManifestosFragment(), "MANIFESTO_ROOT");
        return true;

      case R.id.bottom_navigation_item_candidates:
        switchFragmentWithTag(new DistrictsFragment(), "CANDIDATES_ROOT");
        return true;

      case R.id.bottom_navigation_item_more:
        switchFragmentWithTag(new SettingsFragment(), "SETTINGS_ROOT");
        return true;

        default:
          return false;
    }
  }


  private void switchFragment(Fragment fragment) {
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.current_fragment, fragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  private void switchFragmentWithTag(Fragment fragment, String tag) {
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.current_fragment, fragment);
    fragmentTransaction.addToBackStack(tag);
    fragmentTransaction.commit();
  }

  private @Nullable Fragment findCachedFragmentByTag(String tag) {
    FragmentManager manager = getSupportFragmentManager();
    return manager.findFragmentByTag(tag);
  }

  public void showEventDetailPage() {
    switchFragment(new EventDetailFragment());
  }

  public void showCandidateDetailPage() {
    switchFragment(new CandidateDetailFragment());
  }

  public void showManifestoDetail() {
    switchFragment(new ManifestoDetailFragment());
  }


}
