package com.mru.ptr.district.ui.repository.webservice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-20.
 */
public class MockDistrictWebService implements DistrictWebService {

  private HashMap<String, List<CandidateDataModel>> candidatesByDistrict;
  private DistrictWebServiceCallback callback;

  public MockDistrictWebService(DistrictWebServiceCallback callback) {
    candidatesByDistrict = new HashMap<>();
    this.callback = callback;
    populateMockCandidates();
  }

  @Override
  public void fetchAllDistricts() {
    if(this.callback != null) {
      this.callback.onFetched(createMockDistricts());
    }
  }

  @Override
  public void fetchAllCandidatesByDistrict(String districtId) {
    if(this.callback != null){
      this.callback.onAssociatedCandidatesfetched(this.candidatesByDistrict.get(districtId));
    }
  }

  private List<DistrictDataModel> createMockDistricts() {
    List<DistrictDataModel> districts = new ArrayList<>();
    districts.add(new DistrictDataModel().setId("1").setName("CLP 1"));
    districts.add(new DistrictDataModel().setId("2").setName("CLP 2"));
    districts.add(new DistrictDataModel().setId("3").setName("CLP 3"));

    return districts;
  }


  private void populateMockCandidates() {

    List<CandidateDataModel> firstDistrictCandidates = new ArrayList<>();

    firstDistrictCandidates.add(new CandidateDataModel().setName("Navin").setFamilyName("Ramgoolam").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    firstDistrictCandidates.add(new CandidateDataModel().setName("Shakeel").setFamilyName("Mohammed").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    firstDistrictCandidates.add(new CandidateDataModel().setName("Rama").setFamilyName("Sitanen").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    firstDistrictCandidates.add(new CandidateDataModel().setName("Xavier").setFamilyName("Duval").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    firstDistrictCandidates.add(new CandidateDataModel().setName("Arvin").setFamilyName("Boolel").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));

    candidatesByDistrict.put("1", firstDistrictCandidates);

    List<CandidateDataModel> secondDistrictCandidates = new ArrayList<>();

    secondDistrictCandidates.add(new CandidateDataModel().setName("Navin").setFamilyName("Ramgoolam").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    secondDistrictCandidates.add(new CandidateDataModel().setName("Shakeel").setFamilyName("Mohammed").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    secondDistrictCandidates.add(new CandidateDataModel().setName("Rama").setFamilyName("Sitanen").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    secondDistrictCandidates.add(new CandidateDataModel().setName("Xavier").setFamilyName("Duval").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    secondDistrictCandidates.add(new CandidateDataModel().setName("Arvin").setFamilyName("Boolel").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));


    candidatesByDistrict.put("2", secondDistrictCandidates);

    List<CandidateDataModel> thirdDistrictCandidates = new ArrayList<>();

    thirdDistrictCandidates.add(new CandidateDataModel().setName("Navin").setFamilyName("Ramgoolam").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    thirdDistrictCandidates.add(new CandidateDataModel().setName("Shakeel").setFamilyName("Mohammed").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    thirdDistrictCandidates.add(new CandidateDataModel().setName("Rama").setFamilyName("Sitanen").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    thirdDistrictCandidates.add(new CandidateDataModel().setName("Xavier").setFamilyName("Duval").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));
    thirdDistrictCandidates.add(new CandidateDataModel().setName("Arvin").setFamilyName("Boolel").setDescription("Some description").setImageUrl("https://firebasestorage.googleapis.com/v0/b/ptr-admin-web.appspot.com/o/72944274_10157825455866474_801003364820189184_n.jpg?alt=media&token=26265388-5a47-461b-97c7-05bfe578aa5d"));


    candidatesByDistrict.put("3", thirdDistrictCandidates);

  }


  @Override
  public void cleanWebServiceCallback() {
    this.callback = null;
  }
}
