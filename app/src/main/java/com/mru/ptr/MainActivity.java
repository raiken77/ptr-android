package com.mru.ptr;

import android.os.Build.VERSION_CODES;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.mru.ptr.district.ui.CandidateDetailFragment;
import com.mru.ptr.district.ui.CandidatesFragment;
import com.mru.ptr.district.ui.DistrictsFragment;
import com.mru.ptr.event.ui.EventDetailFragment;
import com.mru.ptr.event.ui.EventsFragment;
import com.mru.ptr.gallery.ui.GalleryFrament;
import com.mru.ptr.manifesto.ui.ManifestoDetailFragment;
import com.mru.ptr.manifesto.ui.ManifestosCategoriesFragment;
import com.mru.ptr.manifesto.ui.ManifestosFragment;
import com.mru.ptr.settings.ui.SettingsFragment;
import com.mru.ptr.videoplayer.VideoPlayerFragment;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

  private FragmentManager fragmentManager;
  private FragmentTransaction fragmentTransaction;
  private BottomNavigationView bottomNavigationView;
  private static final String ROOT_VIEW = "ROOT_VIEW";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTheme(R.style.AppTheme);
    setContentView(R.layout.activity_main);
    Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
    setSupportActionBar(myToolbar);

    myToolbar.setNavigationOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });

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
    Fragment selectedFragment = getClickedBottomBarItemFragment(menuItemId);
    if(selectedFragment != null) {
      switchBottomBarFragment(selectedFragment);
      return true;
    }

    return false;
  }

  private Fragment getClickedBottomBarItemFragment(int menuItemId) {
    switch(menuItemId) {
      case R.id.bottom_navigation_item_events:
        return new EventsFragment();

      case R.id.bottom_navigation_item_gallery:
        return new GalleryFrament();
      case R.id.bottom_navigation_item_manifesto:
        return new ManifestosCategoriesFragment();

      case R.id.bottom_navigation_item_candidates:
        return new DistrictsFragment();

      case R.id.bottom_navigation_item_more:
        return new SettingsFragment();

      default:
        return null;
    }
  }

  private void switchBottomBarFragment(Fragment fragment) {
    fragmentManager.popBackStack(ROOT_VIEW, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    fragmentTransaction.replace(R.id.current_fragment, fragment);
    fragmentTransaction.addToBackStack(ROOT_VIEW);
    fragmentTransaction.commit();
  }


  private void switchFragment(Fragment fragment) {
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.current_fragment, fragment);
    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  public void showEventDetailPage(View sharedElement) {
    Fragment chooserFragment = fragmentManager.findFragmentById(R.id.current_fragment);
    Fragment fragment = new EventDetailFragment();
//    // Set up the transaction.
    FragmentTransaction transaction = fragmentManager.beginTransaction();


    if (android.os.Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
//      // only for gingerbread and newer versions
//
//      // Define the shared element transition.
      fragment.setSharedElementEnterTransition(new RowDetailTransition());
      fragment.setSharedElementReturnTransition(new RowDetailTransition());
//
//      // The rest of the views are just fading in/out.
//      fragment.setEnterTransition(new Fade());
//      chooserFragment.setExitTransition(new Fade());
//
//      // Now use the image's view and the target transitionName to define the shared element.
      transaction.addSharedElement(sharedElement, "bar");
//    }
//
//    // Replace the fragment.

  }
    transaction.replace(R.id.current_fragment, fragment);
//
//    // Enable back navigation with shared element transitions.
    transaction.addToBackStack(null);
//
//    // Finally press play.
    transaction.commit();
  }

  public void showCandidateDetailPage(View sharedElement) {
   switchFragment(new CandidateDetailFragment());
  }


  public void showManifestoDetail() {
    switchFragment(new ManifestoDetailFragment());
  }

  public void showManifestos() {
    switchFragment(new  ManifestosFragment());
  }

  public void showCandidatesForDistrictPage() {
    switchFragment(new CandidatesFragment());
  }

  public void playVideo(String url) {
    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.setCustomAnimations(
      R.anim.slide_up,
      R.anim.slide_down,
      R.anim.slide_up,
      R.anim.slide_down
    );
    fragmentTransaction.add(R.id.video_player, VideoPlayerFragment.newInstance(url));
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }


  @Override
  public void onBackPressed() {
    if(fragmentManager.getBackStackEntryCount() == 1) {
      finish();
    }
    else {
      super.onBackPressed();
    }
  }
}
