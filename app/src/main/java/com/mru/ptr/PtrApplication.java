package com.mru.ptr;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;
import com.mru.ptr.database.SingletonDatabaseInstance;

/**
 * Created by Jonathan on 2019-10-17.
 */
public class PtrApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//    SingletonDatabaseInstance.getInstance().initialiseDatabase(this);
  }
}
