package com.mru.ptr.database;

import android.content.Context;
import androidx.room.Room;

/**
 * Created by Jonathan on 2019-10-21.
 */
public class SingletonDatabaseInstance {
  public final static String DATABASE_NAME = "alliance_nationale_database";
  private  PTRDatabase singletonPTRInstance;
  private static SingletonDatabaseInstance singletonDatabaseInstance;


  public static SingletonDatabaseInstance getInstance() {
    if(singletonDatabaseInstance == null) {
      singletonDatabaseInstance = new SingletonDatabaseInstance();
    }

    return singletonDatabaseInstance;
  }


  public void initialiseDatabase(Context applicationContext){
    this.singletonPTRInstance = Room.databaseBuilder(applicationContext, PTRDatabase.class, DATABASE_NAME).build();
  }


  public PTRDatabase getRoomDatabase() {
    return this.singletonPTRInstance;
  }
}
