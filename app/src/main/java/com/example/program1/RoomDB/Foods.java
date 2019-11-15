package com.example.program1.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Foods {
  @PrimaryKey (autoGenerate = true)
  @ColumnInfo (name = "foods_id")
  private int id;

  @ColumnInfo(name = "foods_name")
  private String name;

  @ColumnInfo(name = "foods_price")
  private int price;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }
}
