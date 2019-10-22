package com.mru.ptr.event.ui.repository.webservice;

import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.event.ui.model.EventDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-15.
 */
public interface EventWebServiceCallback  extends GenericWebserviceCallback<List<EventDataModel>> {

  void onDesctiptionFetched(String description);
}
