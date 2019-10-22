package com.mru.ptr;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;
import com.mru.ptr.database.SingletonDatabaseInstance;
import com.mru.ptr.executor.PTRExecutor;

/**
 * Created by Jonathan on 2019-10-17.
 */
public class PtrApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    PTRExecutor.initialiseExecutors();
//    SingletonDatabaseInstance.getInstance().initialiseDatabase(this);
  }
}
