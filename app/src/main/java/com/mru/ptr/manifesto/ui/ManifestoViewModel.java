package com.mru.ptr.manifesto.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mru.ptr.Response;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import com.mru.ptr.manifesto.ui.repository.ManifestoRepository;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class ManifestoViewModel extends ViewModel {

  private ManifestoRepository repository;
  private final MutableLiveData<ManifestoCategoryDataModel> selectedCategory;
  private final MutableLiveData<ManifestoDataModel> selectedManifesto;
  public LiveData<List<ManifestoCategoryDataModel>> manifestoCategories;

  public ManifestoViewModel() {
    this.repository = new ManifestoRepository();
    selectedCategory = new MutableLiveData<>();
    selectedManifesto = new MutableLiveData<>();
    manifestoCategories = this.repository.categories;
    this.repository.fetchAllManifestoCategories();
  }



  public LiveData<List<ManifestoDataModel>> getAllManifestosByCategory(String categoryId) {
    repository.fetchAllManifestosByCategory(categoryId);
    return repository.manifestos;
  }

  public void selectCategory(ManifestoCategoryDataModel dataModel) {
    selectedCategory.setValue(dataModel);
  }

  public void selectManifesto(ManifestoDataModel manifestoDataModel) {
    selectedManifesto.setValue(manifestoDataModel);
  }

  public LiveData<ManifestoCategoryDataModel> getSelectedCategory() {
    return this.selectedCategory;
  }

  public LiveData<ManifestoDataModel> getSelectedManifesto() {
    return this.selectedManifesto;
  }

  @Override
  protected void onCleared() {
    if(this.repository != null) {
      this.repository.cleanup();
    }
    super.onCleared();
  }
}
