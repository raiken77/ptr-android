package com.mru.ptr.event.ui.repository.webservice;

import androidx.lifecycle.LiveData;
import com.mru.ptr.CleanableService;
import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.Response;
import com.mru.ptr.event.ui.model.EventDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-20.
 */
public interface EventWebService extends CleanableService {

  void fetchAllOrderedEvents();

}
