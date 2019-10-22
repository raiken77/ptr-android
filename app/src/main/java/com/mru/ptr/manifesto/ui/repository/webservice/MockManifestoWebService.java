package com.mru.ptr.manifesto.ui.repository.webservice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import com.mru.ptr.district.ui.repository.disk.CandidateDao;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
public class MockManifestoWebService implements ManifestoWebService {

  private HashMap<String, List<ManifestoDataModel>> categoryManifestos;
  private ManifestoWebServiceCallback callback;

  public MockManifestoWebService(ManifestoWebServiceCallback callback) {
    this.callback = callback;
    categoryManifestos = new HashMap<>();
    populateManifestoCategories();
  }

  @Override
  public void fetchManifestoCategories() {
    if(callback != null) {
      callback.onFetched(createMockManifestoCategory());
    }
  }

  @Override
  public void fetchManifestosByCategoryId(String categoryId) {
    if(callback != null) {
      callback.onManifestosRetrieved(this.categoryManifestos.get(categoryId));
    }
  }

  private List<ManifestoCategoryDataModel> createMockManifestoCategory() {
    List<ManifestoCategoryDataModel> categoryDataModels = new ArrayList<>();

    categoryDataModels.add(new ManifestoCategoryDataModel().setId("1").setName("Business"));
    categoryDataModels.add(new ManifestoCategoryDataModel().setId("2").setName("Economy"));

    return categoryDataModels;
  }

  private void populateManifestoCategories() {
    List<ManifestoDataModel> businessManifestos = new ArrayList<>();
    businessManifestos.add(new ManifestoDataModel().setDescription("Some description").setTitle("Business manifesto"));
    businessManifestos.add(new ManifestoDataModel().setDescription("Some description").setTitle("Business manifesto"));
    businessManifestos.add(new ManifestoDataModel().setDescription("Some description").setTitle("Business manifesto"));
    businessManifestos.add(new ManifestoDataModel().setDescription("Some description").setTitle("Business manifesto"));
    businessManifestos.add(new ManifestoDataModel().setDescription("Some description").setTitle("Business manifesto"));
    businessManifestos.add(new ManifestoDataModel().setDescription("Some description").setTitle("Business manifesto"));

    this.categoryManifestos.put("1", businessManifestos);


    List<ManifestoDataModel> Economy = new ArrayList<>();
    Economy.add(new ManifestoDataModel().setDescription("Some description").setTitle("Economy manifesto"));
    Economy.add(new ManifestoDataModel().setDescription("Some description").setTitle("Economy manifesto"));
    Economy.add(new ManifestoDataModel().setDescription("Some description").setTitle("Economy manifesto"));
    Economy.add(new ManifestoDataModel().setDescription("Some description").setTitle("Economy manifesto"));
    Economy.add(new ManifestoDataModel().setDescription("Some description").setTitle("Economy manifesto"));
    Economy.add(new ManifestoDataModel().setDescription("Some description").setTitle("Economy manifesto"));

    this.categoryManifestos.put("2", businessManifestos);
  }

  @Override
  public void cleanWebServiceCallback() {

  }
}
