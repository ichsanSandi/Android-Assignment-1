package com.example.program1.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
  @Query("SELECT * FROM users")
  List<User> getAll();

  @Query("SELECT * FROM users WHERE user_id IN (:userIds)")
  List<User> loadAllByIds(int[] userIds);

  @Query("SELECT * FROM users WHERE user_first_name LIKE :first AND " +
          "user_last_name LIKE :last LIMIT 1")
  User findByName(String first, String last);

  @Insert
  void insertAll(User... users);

  @Delete
  void delete(User user);
}
