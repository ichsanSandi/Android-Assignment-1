package com.example.program1.RoomDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database (entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase
{
  public abstract UserDao userDao ();
}
