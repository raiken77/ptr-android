package com.mru.ptr.manifesto.ui.repository.disk;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
@Dao
public interface ManifestoDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void save(List<ManifestoDataModel> manifestoDataModels);

  @Query("SELECT * from manifesto where manifesto_category_id=:categoryId")
  LiveData<List<ManifestoDataModel>> loadManifestos(String categoryId);
}
