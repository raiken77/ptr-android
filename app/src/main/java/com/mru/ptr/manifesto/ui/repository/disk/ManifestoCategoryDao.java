package com.mru.ptr.manifesto.ui.repository.disk;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
@Dao
public abstract class ManifestoCategoryDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void save(List<ManifestoCategoryDataModel> manifestoCategoryDataModels);

  @Query("SELECT * from manifesto_category")
  public abstract LiveData<List<ManifestoCategoryDataModel>> load();

  @Query("DELETE FROM manifesto_category")
  public abstract void deleteAll();


  @Transaction
  public void updateData(List<ManifestoCategoryDataModel> districtDataModels) {
    deleteAll();
    save(districtDataModels);
  }

}
