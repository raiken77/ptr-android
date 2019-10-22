package com.mru.ptr.district.ui.repository.webservice;

import androidx.lifecycle.LiveData;
import com.mru.ptr.CleanableService;
import com.mru.ptr.Response;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-20.
 */
public interface DistrictWebService extends CleanableService {

  void fetchAllDistricts();

  void fetchAllCandidatesByDistrict(String districtId);

}
