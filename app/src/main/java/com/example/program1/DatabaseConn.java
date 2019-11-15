package com.example.program1;

import android.annotation.SuppressLint;
import android.app.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressLint("Registered")
public class DatabaseConn extends Application {

  public Connection postgreConn()
  {
    String url = "jdbc:postgresql://10.0.30.53:5432/db_exploration";
    String username = "irest";
    String pwd = "123456";
    try
    {
      Connection conn = DriverManager.getConnection(url, username, pwd);
      return conn;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
