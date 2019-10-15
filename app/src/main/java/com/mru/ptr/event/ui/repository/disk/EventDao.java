package com.mru.ptr.event.ui.repository.disk;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.mru.ptr.event.ui.model.EventDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
@Dao
public abstract class EventDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void save(List<EventDataModel> eventDataModels);

  @Query("SELECT * from event")
  public abstract LiveData<List<EventDataModel>> load();

  @Query("DELETE FROM event")
  public abstract void deleteAll();


  @Transaction
  public void updateData(List<EventDataModel> eventDataModels) {
    deleteAll();
    save(eventDataModels);
  }
}
