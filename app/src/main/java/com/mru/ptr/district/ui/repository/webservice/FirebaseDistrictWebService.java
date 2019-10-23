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
  private DistrictWebServiceCallback callback;

  private ValueEventListener districtValueListener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      List<DistrictDataModel> districtsFetched = new ArrayList<>();

      for(DataSnapshot currentNode : dataSnapshot.getChildren()) {
        districtsFetched.add(currentNode.getValue(DistrictDataModel.class));
      }

      callback.onFetched(districtsFetched);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
  };

  private ValueEventListener candidatesValueListener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      List<CandidateDataModel> candidateDataModels = new ArrayList<>();

      for(DataSnapshot child : dataSnapshot.getChildren()) {
        candidateDataModels.add(child.getValue(CandidateDataModel.class));
      }

      callback.onAssociatedCandidatesfetched(candidateDataModels);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
  };

  public FirebaseDistrictWebService(DistrictWebServiceCallback callback) {
    this.callback = callback;
  }

  @Override
  public void fetchAllCandidatesByDistrict(
    String districtId) {
    String newPath = String.format(Locale.ENGLISH, "%s/%s",CANDIDATES_PATH, districtId);

    FirebaseDatabase.getInstance().getReference(newPath).addListenerForSingleValueEvent(candidatesValueListener);

  }

  @Override
  public void fetchAllDistricts() {

    FirebaseDatabase.getInstance().getReference(DISTRICTS_PATH).addValueEventListener(districtValueListener);

  }

  @Override
  public void cleanWebServiceCallback() {
    this.callback = null;
  }
}
