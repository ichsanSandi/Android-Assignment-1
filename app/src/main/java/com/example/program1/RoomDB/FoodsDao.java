package com.example.program1.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodsDao {
  @Query("SELECT * FROM Foods")
  List<Foods> getAll();

  @Insert
  void insertFood(Foods foods);

  @Query("SELECT * FROM Foods WHERE foods_id = (:id)")
  List<Foods> getNameFromId(int id);

  @Update
  void updateFood (Foods foods);

  @Delete
  void deleteFood (Foods foods);

}
