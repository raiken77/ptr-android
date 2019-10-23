package com.mru.ptr.district.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.Response;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import com.mru.ptr.district.ui.repository.disk.CandidateDao;
import com.mru.ptr.district.ui.repository.disk.DistrictDao;
import com.mru.ptr.district.ui.repository.webservice.DistrictWebService;
import com.mru.ptr.district.ui.repository.webservice.DistrictWebServiceCallback;
import com.mru.ptr.district.ui.repository.webservice.FirebaseDistrictWebService;
import com.mru.ptr.district.ui.repository.webservice.MockDistrictWebService;
import com.mru.ptr.executor.PTRExecutor;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class DistrictRepository implements DistrictWebServiceCallback {

  private DistrictWebService districtWebService;
  private CandidateDao candidateDao;
  private DistrictDao districtDao;

  public MutableLiveData<List<DistrictDataModel>> districts = new MutableLiveData<>();
  public MutableLiveData<List<CandidateDataModel>> candidates = new MutableLiveData<>();

  public DistrictRepository() {
    this.districtWebService = new FirebaseDistrictWebService(this);
  }

  public void fetchAllDistricts() {
    this.districtWebService.fetchAllDistricts();
  }

  public void getchAllCandidatesByDistrict(String id) {
    this.districtWebService.fetchAllCandidatesByDistrict(id);
  }

  @Override
  public void onFetched(final List<DistrictDataModel> data) {
    districts.setValue(data);
  }

  @Override
  public void onFetchedFailed(String message) {

  }

  @Override
  public void onAssociatedCandidatesfetched(final List<CandidateDataModel> candidateDataModels) {
    candidates.setValue(candidateDataModels);
  }

  public void cleanup() {
    this.districtWebService.cleanWebServiceCallback();
  }
}
