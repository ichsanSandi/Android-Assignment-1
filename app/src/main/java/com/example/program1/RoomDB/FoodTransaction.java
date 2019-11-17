package com.example.program1.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (foreignKeys = {@ForeignKey(entity = User.class,
        parentColumns = "user_id",
        childColumns = "foodTransaction_user_id"),
        @ForeignKey (entity = Foods.class,
                parentColumns = "foods_id",
                childColumns = "foodTransaction_food_id")})

public class FoodTransaction
{
  @PrimaryKey
  @ColumnInfo (name = "foodTransaction_id")
  public String id;

  @PrimaryKey
  @ColumnInfo (name = "foodTransaction_food_id")
  public String food_id;

  @PrimaryKey
  @ColumnInfo (name = "foodTansaction_user_id")
  public int user_id;

  @ColumnInfo (name = "foodTransaction_orderAmount")
  public int orderAmount;

  @ColumnInfo (name = "foodTransaction_orderStatus")
  public String orderStatus;
}
