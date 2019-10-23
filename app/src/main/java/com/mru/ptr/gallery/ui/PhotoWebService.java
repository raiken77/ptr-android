package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import com.mru.ptr.CleanableService;
import com.mru.ptr.Response;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
public interface PhotoWebService extends CleanableService {

  void fetchAllPhotos();
}
