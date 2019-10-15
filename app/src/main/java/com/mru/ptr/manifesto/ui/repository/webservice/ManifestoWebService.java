package com.mru.ptr.manifesto.ui.repository.webservice;

import androidx.lifecycle.LiveData;
import com.mru.ptr.Response;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
public interface ManifestoWebService {

  LiveData<Response<List<ManifestoCategoryDataModel>>> fetchManifestoCategories();
  LiveData<Response<List<ManifestoDataModel>>> fetchManifestosByCategoryId(String categoryId);
}
