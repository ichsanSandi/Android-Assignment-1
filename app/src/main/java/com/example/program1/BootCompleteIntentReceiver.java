package com.example.program1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BootCompleteIntentReceiver extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    if ("android.intent.action.BOOT_COMPLETED".equals (intent.getAction ()))
    {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
      {
        Intent serviceRebootIntent = new Intent(context, ForegroundService.class);
        serviceRebootIntent.putExtra ("inputExtra", "Foreground Service Example in Android");
        context.startForegroundService(serviceRebootIntent);
      }
    }
  }
}
