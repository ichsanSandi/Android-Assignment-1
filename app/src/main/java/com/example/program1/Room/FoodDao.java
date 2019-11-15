package com.example.program1.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM foods")
    List<Foods> getAll();

    @Query("SELECT * FROM foods WHERE uid IN (:foodsIds)")
    List<Foods> loadAllByIds(int[] foodsIds);

    @Query("SELECT * FROM foods WHERE name LIKE :first AND " +
            "price LIKE :last LIMIT 1")
    Foods findByName(String first, String last);

    @Insert
    void insertAll(Foods... foodss);

    @Insert
    void addFoods(Foods foodss);

    @Delete
    void delete(Foods foods);

    @Update
    void update(Foods foods);
}
