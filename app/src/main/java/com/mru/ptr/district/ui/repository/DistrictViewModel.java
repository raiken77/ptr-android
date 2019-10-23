package com.mru.ptr.district.ui.repository;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.mru.ptr.Response;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class DistrictViewModel extends ViewModel {

  private LiveData<List<DistrictDataModel>> districtData;
  private final MutableLiveData<CandidateDataModel> selectedCandidate;
  private final MutableLiveData<DistrictDataModel> selectedDataModel;
  private DistrictRepository districtRepository;
  public MutableLiveData<List<DistrictDataModel>> districtsData;
  private MutableLiveData<String> districtId = new MutableLiveData<>();

  public DistrictViewModel() {

    districtData = new MutableLiveData<>();
    selectedDataModel = new MutableLiveData<>();
    districtRepository = new DistrictRepository();
    selectedCandidate = new MutableLiveData<>();
    districtsData = districtRepository.districts;
    districtRepository.fetchAllDistricts();
  }



  public LiveData<List<CandidateDataModel>> fetchCandidatesByDistrict(String districtId) {

    districtRepository.getchAllCandidatesByDistrict(districtId);
    return districtRepository.candidates;
  }

  public void selectDistrict(DistrictDataModel district) {
    selectedDataModel.setValue(district);
  }

  public LiveData<DistrictDataModel> getSelected() {
    return selectedDataModel;
  }

  public void selectCandidate(CandidateDataModel dataModel) {
    selectedCandidate.setValue(dataModel);
  }

  public LiveData<CandidateDataModel> getSelectedCandidate() {
    return selectedCandidate;
  }

  @Override
  protected void onCleared() {
    if(districtRepository != null) {
      districtRepository.cleanup();
    }
    super.onCleared();
  }
}
