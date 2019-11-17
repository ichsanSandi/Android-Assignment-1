package com.example.program1.RoomDB;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database (entities = Foods.class, version = 2, exportSchema = false)
public abstract class FoodsDatabase extends RoomDatabase
{
  public abstract FoodsDao FoodsDao ();

  public static final Migration MIGRATION_1_2 = new Migration (1, 2)
  {
    @Override
    public void migrate (@NonNull SupportSQLiteDatabase database)
    {
      database.execSQL ("alter table foods add column foods_server_id text");
    }
  };
}