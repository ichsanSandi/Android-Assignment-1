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

    String connectionUrl = String.valueOf(R.string.connectionURL);
    String connectionUsername = String.valueOf(R.string.connectionUserName);
    String connectionPassword = String.valueOf(R.string.connectionPassword);
    
    try
    {
      return DriverManager.getConnection (connectionUrl, connectionUsername, connectionPassword);
    }
    catch (SQLException e)
    {
      e.printStackTrace ();
    }
    return null;
  }
}
