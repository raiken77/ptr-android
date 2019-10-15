package com.mru.ptr.manifesto.ui.repository.webservice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
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

  public MockManifestoWebService() {
    categoryManifestos = new HashMap<>();
    populateManifestoCategories();
  }

  @Override
  public LiveData<Response<List<ManifestoCategoryDataModel>>> fetchManifestoCategories() {
    final MutableLiveData<Response<List<ManifestoCategoryDataModel>>> responseMutableLiveData = new MutableLiveData<>();
    Response<List<ManifestoCategoryDataModel>> categoryResponse = new Response<>();
    categoryResponse
      .setData(createMockManifestoCategory())
      .setStatus(ResponseStatus.SUCCESS)
      .setErrorMessage(null);

    responseMutableLiveData.setValue(categoryResponse);
    return responseMutableLiveData;
  }

  @Override
  public LiveData<Response<List<ManifestoDataModel>>> fetchManifestosByCategoryId(String categoryId) {
    final MutableLiveData<Response<List<ManifestoDataModel>>> responseMutableLiveData = new MutableLiveData<>();
    Response<List<ManifestoDataModel>> categoryResponse = new Response<>();

    categoryResponse
      .setData(this.categoryManifestos.get(categoryId))
      .setStatus(ResponseStatus.SUCCESS)
      .setErrorMessage(null);

    responseMutableLiveData.setValue(categoryResponse);
    return responseMutableLiveData;
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


}
