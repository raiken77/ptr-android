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
  private ValueEventListener manifestoCategoriesListener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      List<ManifestoCategoryDataModel> categories = new ArrayList<>();

      for (DataSnapshot child : dataSnapshot.getChildren()) {
        categories.add(child.getValue(ManifestoCategoryDataModel.class));
      }

      if(callback != null) {
        callback.onFetched(categories);
      }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
  };

  private ValueEventListener manifestoListener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      List<ManifestoDataModel> manifestos = new ArrayList<>();

      for(DataSnapshot child : dataSnapshot.getChildren()) {
        manifestos.add(child.getValue(ManifestoDataModel.class));
      }
      if(callback != null) {
        callback.onManifestosRetrieved(manifestos);
      }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
  };

  private ManifestoWebServiceCallback callback;

  public FirebaseManifestoWebService(ManifestoWebServiceCallback callback) {
    this.callback = callback;
  }

  @Override
  public void fetchManifestoCategories() {
    FirebaseDatabase.getInstance().getReference(MANIFESTO_CATEGORIES_PATH).addValueEventListener(manifestoCategoriesListener);
  }

  @Override
  public void fetchManifestosByCategoryId(final String categoryId) {
    String newPath = String.format(Locale.ENGLISH, "%s/%s", MANIFESTO_PATH, categoryId);

    FirebaseDatabase.getInstance().getReference(newPath).addValueEventListener(manifestoListener);

  }

  @Override
  public void cleanWebServiceCallback() {
    this.callback = null;
  }
}
