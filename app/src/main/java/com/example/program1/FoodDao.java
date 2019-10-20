package com.example.program1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {
    @Query("Select * from Food")
    List<Food> getFoodList();

    @Insert
    void insertFood(Food food);

    @Delete
    void deleteFood(Food food);

    @Update
    void updateFood(Food food);
}
