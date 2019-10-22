package com.mru.ptr.district.ui.repository.webservice;

import com.mru.ptr.GenericWebserviceCallback;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-22.
 */
public interface DistrictWebServiceCallback extends GenericWebserviceCallback<List<DistrictDataModel>> {

  void onAssociatedCandidatesfetched(List<CandidateDataModel> candidateDataModels);


}
