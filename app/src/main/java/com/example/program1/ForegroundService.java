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

public class ForegroundService extends Service {
  public static final String CHANNEL_ID = "ForegroundServiceChannel";
  public static final String TAG = "ForegroundService";

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {

    createNotificationChannel();
    Intent notificationIntent = new Intent(this, HalamanMasuk.class);
    String text = intent.getStringExtra("inputExtra");
    PendingIntent pendingIntent = PendingIntent.getActivity(this,
            0, notificationIntent, 0);

    Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build();

    Toast.makeText(this, "Notification Service started by user.", Toast.LENGTH_LONG).show();
    for (int i = 0; i < Integer.MAX_VALUE; i++)
    {
      Log.w(TAG, String.valueOf(i));
    }
//    DatabaseConn dbConn = new DatabaseConn();
//    dbConn.postgreConn();


    startForeground(1, notification);

    //do heavy work on a background thread


    //stopSelf();

    return START_STICKY;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  private void createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel serviceChannel = new NotificationChannel(
              CHANNEL_ID,
              "Foreground Service Channel",
              NotificationManager.IMPORTANCE_HIGH
      );
      serviceChannel.setDescription("This is the description of channel notification");
      NotificationManager manager = getSystemService(NotificationManager.class);
      manager.createNotificationChannel(serviceChannel);
    }
  }

  @Override
  public void onTaskRemoved(Intent rootIntent) {
    super.onTaskRemoved(rootIntent);
  }
}