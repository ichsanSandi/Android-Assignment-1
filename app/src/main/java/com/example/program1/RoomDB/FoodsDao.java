package com.example.program1.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface FoodsDao
{
  @Query ("SELECT * FROM Foods")
  List<Foods> getAll ();

  @Query("SELECT foods_server_id FROM Foods")
  List<String> getFoodsServer_id ();

  @Query("SELECT * FROM Foods WHERE foods_server_id IS null")
  List<Foods> getUnsyncObject();

  @Insert
  void insertFood (Foods foods);

  @Query ("SELECT * FROM Foods WHERE foods_id LIKE :id")
  List<Foods> getNameFromId (int id);

  @Update
  void updateFood (Foods foods);

  @Delete
  void deleteFood (Foods foods);
}
