package com.mru.ptr.manifesto.ui.repository.webservice;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mru.ptr.Response;
import com.mru.ptr.ResponseStatus;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jonathan on 2019-10-16.
 */
public class FirebaseManifestoWebService implements ManifestoWebService {

  private final static String MANIFESTO_PATH = "Manifesto";
  private final static String MANIFESTO_CATEGORIES_PATH = "ManifestoCategory";

  public FirebaseManifestoWebService() {
  }

  @Override
  public LiveData<Response<List<ManifestoCategoryDataModel>>> fetchManifestoCategories() {
    final MutableLiveData<Response<List<ManifestoCategoryDataModel>>> response = new MutableLiveData<>();

    FirebaseDatabase.getInstance().getReference(MANIFESTO_CATEGORIES_PATH)
      .addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          List<ManifestoCategoryDataModel> categories = new ArrayList<>();
          Response<List<ManifestoCategoryDataModel>> categoryResponse = new Response<>();

          for (DataSnapshot child : dataSnapshot.getChildren()) {
            categories.add(child.getValue(ManifestoCategoryDataModel.class));
          }

          categoryResponse
            .setData(categories)
            .setStatus(ResponseStatus.SUCCESS)
            .setErrorMessage(null);

          response.setValue(categoryResponse);


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
      });

    return response;
  }

  @Override
  public LiveData<Response<List<ManifestoDataModel>>> fetchManifestosByCategoryId(String categoryId) {
    String newPath = String.format(Locale.ENGLISH, "%s/%s", MANIFESTO_PATH, categoryId);

    final MutableLiveData<Response<List<ManifestoDataModel>>> manifestoResponse = new MutableLiveData<>();
    FirebaseDatabase.getInstance().getReference(newPath)
      .addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          List<ManifestoDataModel> manifestos = new ArrayList<>();
          Response<List<ManifestoDataModel>> response = new Response<>();

          for(DataSnapshot child : dataSnapshot.getChildren()) {
            manifestos.add(child.getValue(ManifestoDataModel.class));
          }

          response
            .setData(manifestos)
            .setStatus(ResponseStatus.SUCCESS)
            .setErrorMessage(null);

          manifestoResponse.setValue(response);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
      });

    return manifestoResponse;
  }
}
