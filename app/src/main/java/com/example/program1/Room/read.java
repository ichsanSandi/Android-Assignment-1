package com.example.program1.Room;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.program1.R;
import com.example.program1.adapter.AdapterAdminMinuman;
import com.example.program1.model.Drink;
import com.example.program1.model.Food;
import com.example.program1.view.admin.LihatMenuMinuman;
import com.google.android.gms.internal.firebase_auth.zzdb;

import java.util.ArrayList;
import java.util.List;

public class read extends AppCompatActivity {

    TextView room;
    public static AppDatabase AppDatabase;
    Context context;

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
        List<Foods> users = read.AppDatabase.foodDao().getAll();

        foodArrayList = new ArrayList<Foods>(users);

        myRecyclerViewAdapter = new AdapterRoom(foodArrayList, read.this);

        myRecyclerView.setAdapter(myRecyclerViewAdapter);
    }
}
