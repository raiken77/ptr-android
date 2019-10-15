package com.mru.ptr.district.ui.repository;

import androidx.lifecycle.LiveData;
import com.mru.ptr.Response;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import com.mru.ptr.district.ui.repository.webservice.DistrictWebService;
import com.mru.ptr.district.ui.repository.webservice.MockDistrictWebService;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class DistrictRepository {

  private DistrictWebService districtWebService;

  public DistrictRepository() {
    this.districtWebService = new MockDistrictWebService();
  }

  public LiveData<Response<List<DistrictDataModel>>> fetchAllDistricts() {
    return districtWebService.fetchAllDistricts();
  }

  public LiveData<Response<List<CandidateDataModel>>> getchAllCandidatesByDistrict(String id) {
    return districtWebService.fetchAllCandidatesByDistrict(id);
  }
}
