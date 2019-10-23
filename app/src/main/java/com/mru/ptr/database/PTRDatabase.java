package com.mru.ptr.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.mru.ptr.district.ui.model.CandidateDataModel;
import com.mru.ptr.district.ui.model.DistrictDataModel;
import com.mru.ptr.district.ui.repository.disk.CandidateDao;
import com.mru.ptr.district.ui.repository.disk.DistrictDao;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.event.ui.repository.disk.EventDao;
import com.mru.ptr.gallery.ui.PhotoDao;
import com.mru.ptr.gallery.ui.PhotoDataModel;
import com.mru.ptr.gallery.ui.VideoDao;
import com.mru.ptr.gallery.ui.VideoDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoCategoryDataModel;
import com.mru.ptr.manifesto.ui.model.ManifestoDataModel;
import com.mru.ptr.manifesto.ui.repository.disk.ManifestoCategoryDao;
import com.mru.ptr.manifesto.ui.repository.disk.ManifestoDao;

/**
 * Created by Jonathan on 2019-10-21.
 */
@Database(entities = {
  EventDataModel.class,
  DistrictDataModel.class,
  CandidateDataModel.class,
  PhotoDataModel.class,
  VideoDataModel.class,
  ManifestoDataModel.class,
  ManifestoCategoryDataModel.class
},
  version = 2
)
public abstract class PTRDatabase extends RoomDatabase {
  public abstract EventDao eventDao();
  public abstract DistrictDao districtDao();
  public abstract CandidateDao candidateDao();
  public abstract PhotoDao photoDao();
  public abstract VideoDao videoDao();
  public abstract ManifestoDao manifestoDao();
  public abstract ManifestoCategoryDao manifestoCategoryDao();
}
