package com.example.program1.RoomDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class Application extends AppCompatActivity {
  UserDatabase db = Room.databaseBuilder(getApplicationContext(),
          UserDatabase.class, "database-name").build();
}
