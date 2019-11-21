package com.example.program1.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Foods
{
  @NonNull
  @PrimaryKey
  public String uid;

  @ColumnInfo(name = "name")
  public String nameFood;

  @ColumnInfo(name = "price")
  public String priceFood;

  public String getUidFood() { return uid; }

  public void setUidFood(String uid)
  {
    this.uid = uid;
  }

  public String getNameFood()
  {
    return nameFood;
  }

  public void setNameFood(String nameFood)
  {
    this.nameFood = nameFood;
  }

  public String getPriceFood()
  {
    return priceFood;
  }

  public void setPriceFood(String priceFood)
  {
    this.priceFood = priceFood;
  }
}