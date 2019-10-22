package com.mru.ptr.manifesto.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.Response;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import com.mru.ptr.executor.PTRExecutor;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import com.mru.ptr.manifesto.ui.repository.disk.ManifestoCategoryDao;
import com.mru.ptr.manifesto.ui.repository.disk.ManifestoDao;
import com.mru.ptr.manifesto.ui.repository.webservice.ManifestoWebService;
import com.mru.ptr.manifesto.ui.repository.webservice.ManifestoWebServiceCallback;
import com.mru.ptr.manifesto.ui.repository.webservice.MockManifestoWebService;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class ManifestoRepository implements ManifestoWebServiceCallback {

  ManifestoWebService manifestoWebService;
  ManifestoCategoryDao categoryDao;
  ManifestoDao manifestoDao;
  public MutableLiveData<List<ManifestoCategoryDataModel>> categories = new MutableLiveData<>();
  public MutableLiveData<List<ManifestoDataModel>> manifestos = new MutableLiveData<>();

  public ManifestoRepository() {
    this.manifestoWebService = new MockManifestoWebService(this);
  }


  public void fetchAllManifestoCategories(){
    manifestoWebService.fetchManifestoCategories();
  }

  public void fetchAllManifestosByCategory(String categoryId){
    manifestoWebService.fetchManifestosByCategoryId(categoryId);
  }

  @Override
  public void onManifestosRetrieved(final List<ManifestoDataModel> manifestoDataModels) {
    manifestos.setValue(manifestoDataModels);
  }

  @Override
  public void onFetched(final List<ManifestoCategoryDataModel> data) {
    categories.setValue(data);
  }

  @Override
  public void onFetchedFailed(String message) {

  }

  public void cleanup() {
    this.manifestoWebService.cleanWebServiceCallback();
  }
}
