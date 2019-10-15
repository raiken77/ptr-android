package com.mru.ptr.district.ui.repository.webservice;

import androidx.lifecycle.LiveData;
import com.mru.ptr.Response;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-20.
 */
public interface DistrictWebService {

  LiveData<Response<List<DistrictDataModel>>> fetchAllDistricts();

  LiveData<Response<List<CandidateDataModel>>> fetchAllCandidatesByDistrict(String districtId);

}
