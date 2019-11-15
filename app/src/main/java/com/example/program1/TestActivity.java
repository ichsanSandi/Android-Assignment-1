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
import com.example.program1.adapter.AdapterTestView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.program1.RoomDB.FoodsDatabase.MIGRATION_1_2;

public class TestActivity extends AppCompatActivity {

  RecyclerView recyclerView;
  RecyclerView.Adapter recyclerViewAdapter;
  RecyclerView.LayoutManager recyclerViewLayoutMgr;
  Button backBttn;
  FloatingActionButton addBttn;
  List<Foods> foodsList;
  ArrayList<Foods> foodsArrayList;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    startService();
    setContentView(R.layout.test_activity_layout);

    recyclerView = (RecyclerView) findViewById(R.id.itemTestRecyclerView);
    recyclerViewLayoutMgr = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(recyclerViewLayoutMgr);

    FoodsDatabase foodDb = Room.databaseBuilder(getApplicationContext(), FoodsDatabase.class, "foods-database")
            .allowMainThreadQueries()
            .addMigrations(MIGRATION_1_2)
            .build();
    foodsList = foodDb.FoodsDao().getAll();
    foodsArrayList = new ArrayList<Foods>(foodsList);
    recyclerViewAdapter = new AdapterTestView(foodsArrayList, TestActivity.this, foodDb, recyclerViewAdapter);
    recyclerView.setAdapter(recyclerViewAdapter);

    backBttn = (Button) findViewById(R.id.backBttn);
    backBttn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
//        stopService();
        onBackPressed();
      }
    });

    addBttn = (FloatingActionButton) findViewById(R.id.addItemTest);
    addBttn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(TestActivity.this, AddFoodsItemTest.class);
        startActivity(intent);
      }
    });
  }

  private void startService() {
    Intent service = new Intent(TestActivity.this, ForegroundService.class);
    service.putExtra("inputExtra", "Foreground Service Example in Android");
    ContextCompat.startForegroundService(this, service);
  }

  private void stopService() {
    Intent serviceIntent = new Intent(this, ForegroundService.class);
    stopService(serviceIntent);
  }
}