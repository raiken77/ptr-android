package com.mru.ptr.manifesto.ui.repository;

import androidx.lifecycle.LiveData;
import com.mru.ptr.Response;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import com.mru.ptr.manifesto.ui.repository.webservice.ManifestoWebService;
import com.mru.ptr.manifesto.ui.repository.webservice.MockManifestoWebService;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class ManifestoRepository {

  ManifestoWebService manifestoWebService;

  public ManifestoRepository() {
    this.manifestoWebService = new MockManifestoWebService();
  }


  public LiveData<Response<List<ManifestoCategoryDataModel>>> fetchAllManifestoCategories(){
    return manifestoWebService.fetchManifestoCategories();
  }

  public LiveData<Response<List<ManifestoDataModel>>> fetchAllManifestosByCategory(String categoryId){
    return manifestoWebService.fetchManifestosByCategoryId(categoryId);
  }

}
