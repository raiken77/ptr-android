package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
@Dao
public interface PhotoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void save(List<PhotoDataModel> photoDataModels);

  @Query("SELECT * from photo")
  public LiveData<List<PhotoDataModel>> load();

}
