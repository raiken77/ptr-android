package com.mru.ptr.gallery.ui;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
@Dao
public abstract class VideoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract  void save(List<VideoDataModel> videoDataModels);

  @Query("SELECT * from video")
  public abstract LiveData<List<VideoDataModel>> load();

  @Query("DELETE from video")
  public abstract void deleteAll();

  @Transaction
  public void updateData(List<VideoDataModel> videoDataModels) {
    deleteAll();
    save(videoDataModels);
  }
}
