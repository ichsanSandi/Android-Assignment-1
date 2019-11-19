package com.example.program1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import com.example.program1.RoomDB.Foods;
import com.example.program1.RoomDB.FoodsDatabase;
import com.example.program1.view.HalamanMasuk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.program1.RoomDB.FoodsDatabase.MIGRATION_1_2;

public class ForegroundService extends Service
{
  public static final String CHANNEL_ID = "ForegroundServiceChannel";
  public static final String TAG = "ForegroundService";

  List<String> foodsServer_idList;
  ArrayList<String> foodsServer_idArrayList;

  List<Foods> foodsUnsyncDataList;
  ArrayList<Foods> foodsUnsyncDataArrayList;

  @Override
  public void onCreate ()
    { super.onCreate (); }

  @Override
  public int onStartCommand (Intent intentOperand1, int flags, final int startId)
  {
    createNotificationChannel ();
    Intent notificationIntent = new Intent (this, HalamanMasuk.class);
    String textFromIntent = intentOperand1.getStringExtra ("inputExtra");
    PendingIntent pendingIntent1 = PendingIntent.getActivity  (this,
            0, notificationIntent, 0);

    Notification foregroundNotification = new NotificationCompat.Builder (this, CHANNEL_ID)
            .setContentTitle ("Foreground Service")
            .setContentText (textFromIntent)
            .setSmallIcon (R.drawable.ic_launcher_foreground)
            .setContentIntent (pendingIntent1)
            .build ();

    Toast.makeText (this, "Notification Service started by user.", Toast.LENGTH_LONG).show ();

  new Thread
   (
    new Runnable ()
    {
      @Override
      public void run ()
      {
        DatabaseConn dbConn = new DatabaseConn ();
        FoodsDatabase foodsDatabase1 =
         Room.databaseBuilder
         (
          getApplicationContext (),
          FoodsDatabase.class,
          "foods-database"
         )
         .addMigrations(MIGRATION_1_2)
         .allowMainThreadQueries ()
         .build ();

        for (int i = 0; i < 3; i++)
        {
          try
          {
            TimeUnit.SECONDS.sleep (1);
            Log.w (TAG, String.valueOf (i));
          }
          catch (InterruptedException e)
          { e.printStackTrace ();  }
        }
        try
        {
          foodsServer_idList = foodsDatabase1.FoodsDao ().getFoodsServer_id ();
          foodsServer_idArrayList = new ArrayList<> (foodsServer_idList);
          Statement statement1 = dbConn.connection1().createStatement ();
          ResultSet cursor = statement1.executeQuery ("Select * from foods");
          while (cursor.next ())
          {
            if (!foodsServer_idArrayList.contains(cursor.getString(1)))
            {
              Foods foodsObject = new Foods ();
              foodsObject.setServer_id(cursor.getString(1));
              foodsObject.setPrice (Integer.valueOf (cursor.getString(3)));
              foodsObject.setName (cursor.getString (2));
              foodsDatabase1.FoodsDao ().insertFood (foodsObject);
            }
          }
          foodsUnsyncDataList = foodsDatabase1.FoodsDao ().getUnsyncObject ();
          foodsUnsyncDataArrayList = new ArrayList<>(foodsUnsyncDataList);
          Statement statement2 = dbConn.connection1 ().createStatement ();
        }
        catch (SQLException e)
        { e.printStackTrace (); }
      }
    }
   ).start ();
    startForeground ( 1, foregroundNotification);
    return START_STICKY;
  }

  @Override
  public void onDestroy ()
    { super.onDestroy (); }

  @Override
  public IBinder onBind (Intent intent)
    { return null; }

  private void createNotificationChannel ()
  {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
    {
      NotificationChannel processChannel = new NotificationChannel(
              CHANNEL_ID,
              "Foreground Process Channel",
              NotificationManager.IMPORTANCE_HIGH
      );
      processChannel.setDescription ("This is the description of channel notification");
      NotificationManager notificationManager1 = getSystemService (NotificationManager.class);
      notificationManager1.createNotificationChannel (processChannel);
    }
  }

  @Override
  public void onTaskRemoved (Intent rootIntent)
    { super.onTaskRemoved (rootIntent); }
}