package com.example.program1.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Drinks
{
  @PrimaryKey
  @ColumnInfo (name = "drinks_id")
  public String id;

  @ColumnInfo (name = "drinks_name")
  public String name;

  @ColumnInfo (name = "drinks_price")
  public int price;
}
