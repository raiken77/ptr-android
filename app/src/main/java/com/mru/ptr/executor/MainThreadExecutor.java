package com.mru.ptr.executor;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/**
 * Created by Jonathan on 2019-10-22.
 */
public class MainThreadExecutor implements Executor {

  private final Handler handler = new Handler(Looper.getMainLooper());

  @Override
  public void execute(Runnable runnable) {
    handler.post(runnable);
  }
}
