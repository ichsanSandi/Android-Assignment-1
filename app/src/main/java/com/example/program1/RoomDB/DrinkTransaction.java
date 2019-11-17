package com.example.program1.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DrinkTransaction
{
  @PrimaryKey
  @ColumnInfo (name = "drinkTransaction_id")
  public String id;

  @ColumnInfo (name = "drinkTransaction_food_id")
  public String drink_id;

  @ColumnInfo (name = "drinkTansaction_user_id")
  public int user_id;

  @ColumnInfo (name = "drinkTransaction_orderAmount")
  public int orderAmount;

  @ColumnInfo (name = "drinkTransaction_orderStatus")
  public String orderStatus;
}
