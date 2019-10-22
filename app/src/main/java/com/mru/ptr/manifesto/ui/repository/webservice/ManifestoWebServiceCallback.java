package com.mru.ptr.manifesto.ui.repository.webservice;

import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-22.
 */
public interface ManifestoWebServiceCallback extends GenericWebserviceCallback<List<ManifestoCategoryDataModel>> {
  void onManifestosRetrieved(List<ManifestoDataModel> manifestoDataModels);
}
