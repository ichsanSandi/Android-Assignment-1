package com.example.program1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
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

import java.sql.PreparedStatement;
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

  List<Foods> foodsLocalUnsyncDataList;
  ArrayList<Foods> foodsLocalUnsyncDataArrayList;

  List<Integer> foods_idList;
  ArrayList<Integer> foods_idListArrayList;


  @Override
  public void onCreate ()
    { super.onCreate (); }

  @Override
  public int onStartCommand (Intent intentOperand1, int flags, final int startId)
  {
    createNotificationChannel ();
    Intent notificationIntent = new Intent (this, HalamanMasuk.class);
    String textFromIntent = intentOperand1.getStringExtra ("inputExtra");
    PendingIntent pendingIntent1 = PendingIntent.getActivity  (this, 0, notificationIntent, 0);

    Notification foregroundNotification =
    new NotificationCompat.Builder (this, CHANNEL_ID)
    .setContentTitle ("Foreground Service")
    .setContentText (textFromIntent)
    .setSmallIcon (R.drawable.ic_attach_money_black_24dp)
    .setContentIntent (pendingIntent1)
    .build ();

    final Context toastContext = getApplicationContext();
    Toast.makeText (this, "Notification Service started by user.", Toast.LENGTH_LONG).show ();

    Thread syncThread = new Thread ( new Runnable ()
    {
      @Override
      public void run ()
      {
        DatabaseConn dbConn = new DatabaseConn ();
        FoodsDatabase foodsDatabase1 = Room.databaseBuilder(
                getApplicationContext (),
                FoodsDatabase.class,
                "foods-database"
        )
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries ()
                .build ();

        for (int i = 0; i < 10; i++)
        {
          try
          {
            TimeUnit.SECONDS.sleep (1);
            Log.w (TAG, String.valueOf (i));
          }
          catch (InterruptedException errorInterupted)
          { errorInterupted.printStackTrace ();  }

          if (i == 9)
          {
            i = -1;
            try
            {
              foods_idList = foodsDatabase1.FoodsDao ().getFoods_id ();
              foods_idListArrayList = new ArrayList<>(foods_idList);

              foodsLocalUnsyncDataList = foodsDatabase1.FoodsDao ().getLocalUnsyncObject ();
              foodsLocalUnsyncDataArrayList = new ArrayList<> (foodsLocalUnsyncDataList);

              foodsServer_idList = foodsDatabase1.FoodsDao ().getFoodsServer_id ();
              foodsServer_idArrayList = new ArrayList<> (foodsServer_idList);
              Statement statement1 = dbConn.connection1().createStatement ();
              ResultSet cursor1 = statement1.executeQuery ("Select * from foods");
              while (cursor1.next ())
              {
                if (!foodsServer_idArrayList.contains(cursor1.getString(1)))
                {
                  Foods foodsObject = new Foods ();
                  foodsObject.setServer_id(cursor1.getString(1));
                  foodsObject.setPrice (Integer.valueOf (cursor1.getString(3)));
                  foodsObject.setName (cursor1.getString (2));
                  foodsDatabase1.FoodsDao ().insertFood (foodsObject);
                }
              }
              statement1.close ();
              PreparedStatement statement2 = dbConn.connection1 ().prepareStatement ("INSERT INTO Foods(\n" +
                      "\t name, price, id)\n" +
                      "\tVALUES (?, ?, ?);");
              for (int j = 0; j < foodsLocalUnsyncDataArrayList.size(); j++)
              {
                if (foodsLocalUnsyncDataArrayList.get(j).getServer_id() == null)
                {
                  statement2.setString (1, foodsLocalUnsyncDataArrayList.get (j).getName ());
                  statement2.setInt (2, foodsLocalUnsyncDataArrayList.get (j).getPrice ());
                  statement2.setString (3, String.valueOf(foodsLocalUnsyncDataList.get(j).getId ()));
                  statement2.addBatch ();
                }
                statement2.executeBatch ();
              }
              statement2.close ();

              for (int k = 0; k < foodsLocalUnsyncDataArrayList.size();k++)
              {
                PreparedStatement statement3 = dbConn.connection1 (). prepareStatement ("SELECT server_id FROM foods " +
                        "WHERE id = (?)");
                statement3.setString (1, String.valueOf(foodsLocalUnsyncDataArrayList.get (k).getId ()));
                ResultSet cursor2 = statement3.executeQuery();
                while (cursor2.next())
                {
                  foodsLocalUnsyncDataArrayList.get(k).setServer_id(cursor2.getString(1));
                  foodsDatabase1.FoodsDao ().updateFood (foodsLocalUnsyncDataArrayList.get(k));
                }
                System.out.println(foodsLocalUnsyncDataArrayList.get(k).getServer_id());
                System.out.println(foodsLocalUnsyncDataArrayList.get(k).getId());
                statement3.close ();
              }
              dbConn.connection1().close();
            }

            catch (SQLException e)
            {
              Toast.makeText (toastContext, "No connection!", Toast.LENGTH_LONG).show ();
            }
          }
        }
      }
    }
   );
    syncThread.start();
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