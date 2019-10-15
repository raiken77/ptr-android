package com.mru.ptr.district.ui.repository.disk;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-21.
 */
@Dao
public interface CandidateDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void save(List<CandidateDataModel> candidateDataModels);


}
