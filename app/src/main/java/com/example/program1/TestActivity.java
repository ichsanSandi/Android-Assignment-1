package com.example.program1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.program1.RoomDB.Foods;
import com.example.program1.RoomDB.FoodsDatabase;
import com.example.program1.adapter.AdapterItemTestView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.program1.RoomDB.FoodsDatabase.MIGRATION_1_2;

public class TestActivity extends AppCompatActivity
{
  RecyclerView foodsRecyclerView;
  public static RecyclerView.Adapter foodsRecyclerViewAdapter;
  RecyclerView.LayoutManager foodsRecyclerViewLayoutMgr;
  Button backBttn;
  Button startServiceBttn;
  FloatingActionButton addBttn;
  List<Foods> foodsList;
  public static ArrayList<Foods> foodsArrayList;

  @Override
  protected void onCreate (@Nullable Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.test_activity_layout);

    foodsRecyclerView = findViewById (R.id.itemTestRecyclerView);
    foodsRecyclerViewLayoutMgr = new LinearLayoutManager (this);
    foodsRecyclerView.setLayoutManager (foodsRecyclerViewLayoutMgr);

    FoodsDatabase foodDb =
    Room.databaseBuilder (getApplicationContext (), FoodsDatabase.class, "foods-database")
    .allowMainThreadQueries ()
    .addMigrations (MIGRATION_1_2)
    .build ();
    foodsList = foodDb.FoodsDao ().getAll ();
    foodsArrayList = new ArrayList<> (foodsList);
    foodsRecyclerViewAdapter = new AdapterItemTestView(foodsArrayList, TestActivity.this, foodDb, foodsRecyclerViewAdapter);
    foodsRecyclerView.setAdapter (foodsRecyclerViewAdapter);

    backBttn = findViewById (R.id.backBttn);
    backBttn.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        onBackPressed ();
      }
    });

    addBttn = findViewById (R.id.addItemTest);
    addBttn.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View v)
      {
        Intent addFoodsItemIntent = new Intent (TestActivity.this, AddFoodsItemTest.class);
        startActivity (addFoodsItemIntent);
      }
    });

    startServiceBttn = findViewById(R.id.startServiceBttn);
    startServiceBttn.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        startService ();
      }
    });
  }

  @Override
  protected void onRestart()
  {
    super.onRestart();

  }

  private void startService ()
  {
    Intent foregroundServiceIntent = new Intent (TestActivity.this, ForegroundService.class);
    foregroundServiceIntent.putExtra ("inputExtra", "Foreground Service Example in Android");
    ContextCompat.startForegroundService (this, foregroundServiceIntent);
  }

  private void stopService ()
  {
    Intent serviceIntent = new Intent (this, ForegroundService.class);
    stopService (serviceIntent);
  }

  public static void notifyItemInsert(int position)
  {
    foodsRecyclerViewAdapter.notifyItemInserted(position);
    foodsRecyclerViewAdapter.notifyDataSetChanged();
  }
}
