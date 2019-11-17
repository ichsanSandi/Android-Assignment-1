package com.example.program1.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "users")
public class User
{
  @PrimaryKey (autoGenerate = true)
  @ColumnInfo (name = "user_id")
  private int uid;

  @ColumnInfo (name = "user_first_name")
  private String firstName;

  @ColumnInfo (name = "user_last_name")
  private String lastName;

  @ColumnInfo (name = "user_email")
  private String email;

  @ColumnInfo (name = "user_passwood")
  private String password;

  @Ignore
  public boolean isAdmin;

  @Ignore
  public boolean isChef;

  @ColumnInfo (name = "user_role")
  private String role;

  public void setEmail (String email)
    { this.email = email; }

  public String getEmail ()
    { return email; }

  public void setUid (int uid)
    { this.uid = uid; }

  public int getUid ()
    { return uid; }

  public void setFirstName (String firstName)
    { this.firstName = firstName; }

  public String getFirstName ()
    { return firstName; }

  public void setLastName (String lastName)
   { this.lastName = lastName; }

  public String getLastName ()
   { return lastName; }

  public void setPassword (String password)
   { this.password = password; }

  public String getPassword ()
   { return password; }

  public void setRole (String role)
   { this.role = role; }

  public String getRole ()
   { return role; }
}
