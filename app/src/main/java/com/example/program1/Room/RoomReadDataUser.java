package com.example.program1.Room;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.program1.R;
import java.util.ArrayList;
import java.util.List;

public class RoomReadDataUser extends AppCompatActivity {

  TextView room;
  public static AppDatabase AppDatabase;
  AppDatabase db;
  RecyclerView myRecyclerView;
  RecyclerView.Adapter myRecyclerViewAdapter;
  RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
  ArrayList<Foods> foodArrayList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_room_read);
    db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();

    AppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"userdb").allowMainThreadQueries().build();

    myRecyclerView = (RecyclerView) findViewById(R.id.room_recycle);
    myRecyclerView.setHasFixedSize(true);
    myRecyclerViewLayoutMgr = new LinearLayoutManager(this);
    myRecyclerView.setLayoutManager(myRecyclerViewLayoutMgr);
    List<Foods> users = RoomReadDataUser.AppDatabase.foodDao().getAll();

    foodArrayList = new ArrayList<Foods>(users);

    myRecyclerViewAdapter = new AdapterRoom(foodArrayList, RoomReadDataUser.this);

    myRecyclerView.setAdapter(myRecyclerViewAdapter);
  }
}
