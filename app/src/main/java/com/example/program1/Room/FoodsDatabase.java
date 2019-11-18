package com.example.program1.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Foods.class}, version = 1, exportSchema = false)
public abstract class FoodsDatabase extends RoomDatabase
{
  public abstract FoodDao foodDao();
}