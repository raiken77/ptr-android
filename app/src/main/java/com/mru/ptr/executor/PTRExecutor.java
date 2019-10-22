package com.mru.ptr.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jonathan on 2019-10-22.
 */
public class PTRExecutor {

  private static PTRExecutor s_instance;

  public static final int CORES_AVAILABLE = Runtime.getRuntime().availableProcessors();

  private final ThreadPoolExecutor cpuBoundExecutor;

  private final MainThreadExecutor mainThreadExecutor;

  public static void initialiseExecutors() {
    if(s_instance == null) {
      synchronized (PTRExecutor.class) {
        s_instance = new PTRExecutor();
      }
    }
  }

  public static PTRExecutor getInstance() {
    if(s_instance == null) {
      synchronized (PTRExecutor.class) {
        s_instance = new PTRExecutor();
      }
    }
    return s_instance;
  }

  private PTRExecutor() {
    cpuBoundExecutor   = createCpuBoundExecutor();
    mainThreadExecutor = createMainThreadExecutor();
  }


  private ThreadPoolExecutor createCpuBoundExecutor() {
    return new ThreadPoolExecutor(
      CORES_AVAILABLE,
      CORES_AVAILABLE,
      60L,
      TimeUnit.SECONDS,
      new LinkedBlockingQueue<Runnable>());
  }

  private MainThreadExecutor createMainThreadExecutor() {
    return new MainThreadExecutor();
  }


  public ThreadPoolExecutor getCpuBoundExecutor() {
    return cpuBoundExecutor;
  }

  public MainThreadExecutor getMainThreadExecutor() {
    return mainThreadExecutor;
  }
}
