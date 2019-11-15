package com.example.program1.RoomDB;

import androidx.room.DatabaseView;

@DatabaseView("SELECT user_id, user_first_name, user_role AS briefUserDetail FROM users")
public class UserDBView {
  public int uid;
  public String first_name;
  public String urole;
}
