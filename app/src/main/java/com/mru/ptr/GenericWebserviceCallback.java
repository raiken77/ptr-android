package com.mru.ptr;

/**
 * Created by Jonathan on 2019-10-22.
 */
//TODO Learn livedata a bit better to see if we can get rid of this callback
public interface GenericWebserviceCallback<T> {
  void onFetched(T data);
  void onFetchedFailed(String message);
}
