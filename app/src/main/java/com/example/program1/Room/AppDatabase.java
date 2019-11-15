package com.example.program1.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Foods.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FoodDao foodDao();
}