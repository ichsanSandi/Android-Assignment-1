package com.example.program1;

import android.annotation.SuppressLint;
import android.app.Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressLint ("Registered")
public class DatabaseConn extends Application
{
  public Connection connection1()
  {
    String connectionUrl = "jdbc:postgresql://10.0.30.53:5432/";
    String connectionUsername = "db_exploration";
    String connectionPassword = "";
    
    try
    {
      return DriverManager.getConnection (connectionUrl, connectionUsername, connectionPassword);
    }
    catch (SQLException errorSQL)
    {
      errorSQL.printStackTrace ();
    }
    return null;
  }
}
