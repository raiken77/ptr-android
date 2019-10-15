package com.mru.ptr.district.ui.repository.webservice;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class FirebaseDistrictWebService implements DistrictWebService {

  private static final String DISTRICTS_PATH = "Circonscription";
  private static final String CANDIDATES_PATH = "Candidates";


  @Override
  public LiveData<Response<List<CandidateDataModel>>> fetchAllCandidatesByDistrict(
    String districtId) {
    String newPath = String.format(Locale.ENGLISH, "%s/%s",CANDIDATES_PATH, districtId);
    final MutableLiveData<Response<List<CandidateDataModel>>> candidates = new MutableLiveData<>();

    FirebaseDatabase.getInstance().getReference(newPath).addListenerForSingleValueEvent(
      new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          List<CandidateDataModel> candidateDataModels = new ArrayList<>();
          Response<List<CandidateDataModel>> response = new Response<>();

          for(DataSnapshot child : dataSnapshot.getChildren()) {
            candidateDataModels.add(child.getValue(CandidateDataModel.class));
          }

          response
            .setData(candidateDataModels)
            .setStatus(ResponseStatus.SUCCESS)
            .setErrorMessage(null);

          candidates.setValue(response);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
      });
    return candidates;
  }

  @Override
  public LiveData<Response<List<DistrictDataModel>>> fetchAllDistricts() {
    final MutableLiveData<Response<List<DistrictDataModel>>> districts = new MutableLiveData<>();

    FirebaseDatabase.getInstance().getReference(DISTRICTS_PATH).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<DistrictDataModel> districtsFetched = new ArrayList<>();
        Response<List<DistrictDataModel>> response = new Response<>();

        for(DataSnapshot currentNode : dataSnapshot.getChildren()) {
          districtsFetched.add(currentNode.getValue(DistrictDataModel.class));
        }

        response
          .setData(districtsFetched)
          .setStatus(ResponseStatus.SUCCESS)
          .setErrorMessage(null);

        districts.setValue(response);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });

    return districts;
  }

}
