package com.example.program1;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressLint("Registered")
public class DatabaseConn extends Application {

  public void postgreConn()
  {
    String url = "jdbc:postgresql://127.0.0.1:5432/db_exploration";
    String username = "postgres";
    String pwd = "123456";
    try
    {
      Class.forName("org.postgresql.Driver");
      Connection conn = DriverManager.getConnection(url, username, pwd);
      Context context = getApplicationContext();
      CharSequence text = "Connected!";
      int duration = Toast.LENGTH_SHORT;

      Toast toast = Toast.makeText(context, text, duration);
      toast.show();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

}
