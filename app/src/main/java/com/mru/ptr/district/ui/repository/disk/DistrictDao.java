package com.mru.ptr.district.ui.repository.disk;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
@Dao
public abstract class DistrictDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void save(List<DistrictDataModel> photoDataModels);

  @Query("SELECT * from district")
  public abstract LiveData<List<DistrictDataModel>> load();

  @Query("DELETE FROM district")
  public abstract void deleteAll();


  @Transaction
  public void updateData(List<DistrictDataModel> districtDataModels) {
    deleteAll();
    save(districtDataModels);
  }
}
