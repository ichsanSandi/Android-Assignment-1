package com.example.program1.Room;

import android.content.Intent;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.program1.R;

public class Foreground extends AppCompatActivity
{
  private EditText editTextInput;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_foreground);
    editTextInput = findViewById(R.id.edit_text_input);
  }

  public void startService(View v)
  {
    String input = editTextInput.getText().toString();
    Intent serviceIntent = new Intent(this, ExampleService.class);
    serviceIntent.putExtra("inputExtra", input);
    ContextCompat.startForegroundService(this, serviceIntent);
  }

  public void stopService(View v)
  {
    Intent serviceIntent = new Intent(this, ExampleService.class);
    stopService(serviceIntent);
  }
}