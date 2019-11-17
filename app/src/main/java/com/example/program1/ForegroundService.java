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

import com.example.program1.view.HalamanMasuk;

import java.util.concurrent.TimeUnit;

public class ForegroundService extends Service
{
  public static final String CHANNEL_ID = "ForegroundServiceChannel";
  public static final String TAG = "ForegroundService";

  @Override
  public void onCreate ()
  {
    super.onCreate ();
  }

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

    new Thread (new Runnable ()
    {
      @Override
      public void run ()
      {
//        DatabaseConn dbConn = new DatabaseConn ();
//        FoodsDatabase foodsDatabase1 = Room.databaseBuilder(getApplicationContext (), FoodsDatabase.class, "foods-database")
//                .allowMainThreadQueries ()
//                .addMigrations (MIGRATION_1_2)
//                .build ();

        for (int i = 0; i < Integer.MAX_VALUE; i++)
        {
          try
          {
            TimeUnit.SECONDS.sleep (1);
            Log.w (TAG, String.valueOf (i));
          }
          catch (InterruptedException e)
          {
            e.printStackTrace();
          }
        }

//        try
//        {
//          Statement statement1 = dbConn.postgreConn ().createStatement ();
//          ResultSet cursor = statement1.executeQuery ("Select * from public.foods");
//          while (cursor.next ())
//          {
//            Foods foodsObject = new Foods ();
//            //foodsObject.setId(Integer.valueOf(cursor.getString(1)));
//            foodsObject.setPrice (Integer.valueOf (cursor.getString(3)));
//            foodsObject.setName (cursor.getString (2));
//            foodsDatabase1.FoodsDao ().insertFood (foodsObject);
//          }
//        }
//        catch (SQLException e)
//        {
//          e.printStackTrace ();
//        }
      }
    }).start ();
    startForeground ( 1, foregroundNotification);
    return START_STICKY;
  }

  @Override
  public void onDestroy ()
  {
    super.onDestroy ();
  }

  @Override
  public IBinder onBind (Intent intent)
  {
    return null;
  }

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
  {
    super.onTaskRemoved (rootIntent);
  }
}