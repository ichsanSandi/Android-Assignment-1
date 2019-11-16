package com.example.program1;

import android.annotation.SuppressLint;
import android.app.Application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressLint ("Registered")
public class DatabaseConn extends Application
{
  public Connection postgreConn ()
  {
    String connectionUrl = "jdbc:postgresql://10.0.30.53:5432/db_exploration";
    String connectionUsername = "irest";
    String connectionPassword = "123456";
    
    try
    {
      Connection connectionToPostgre = DriverManager.getConnection (connectionUrl, connectionUsername, connectionPassword);
      return connectionToPostgre;
    }
    catch (SQLException e)
    {
      e.printStackTrace ();
    }
    return null;
  }
}
