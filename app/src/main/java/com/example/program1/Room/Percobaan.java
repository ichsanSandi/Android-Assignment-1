package com.example.program1.Room;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.program1.R;

public class Percobaan extends AppCompatActivity
{
  Button ForegroundButton, IntentServiceButton, JobSchedulerButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_percobaan);
    ForegroundButton = findViewById(R.id.ForgroundBtn);
    IntentServiceButton = findViewById(R.id.IntentServiceBtn);
    JobSchedulerButton = findViewById(R.id.JobSchedullerBtn);

    ForegroundButton.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        startActivity(new Intent(Percobaan.this, Foreground.class));
      }
    });
    IntentServiceButton.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        startActivity(new Intent(Percobaan.this, IntentService.class));
      }
    });
    JobSchedulerButton.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        startActivity(new Intent(Percobaan.this, JobSchedulerContoh.class));
      }
    });
  }
}