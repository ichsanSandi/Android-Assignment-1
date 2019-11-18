package com.example.program1.view.konsumen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.program1.R;
import com.example.program1.adapter.AdapterMenuMinuman;
import com.example.program1.model.Drink;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MembeliMinuman extends AppCompatActivity
{
  DatabaseReference databaseReference;
  RecyclerView myRecyclerView;
  RecyclerView.Adapter myRecyclerViewAdapter;
  RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
  ArrayList<Drink> drinkArrayList;
  Button butPesan;

  @Override
  protected void onCreate (@Nullable Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_membeli_minuman);
    myRecyclerView = (RecyclerView) findViewById (R.id.drinkRecyclerView);
    myRecyclerView.setHasFixedSize (true);
    myRecyclerViewLayoutMgr = new LinearLayoutManager (this);
    myRecyclerView.setLayoutManager (myRecyclerViewLayoutMgr);
    butPesan = findViewById (R.id.btn_pesan_minuman);
    databaseReference = FirebaseDatabase.getInstance().getReference();

    databaseReference.child ("drinks").addValueEventListener (new ValueEventListener()
    {
      @Override
      public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
        drinkArrayList = new ArrayList<>();
        for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren())
        {
          System.out.println (dataSnapshotIter.getValue());
          Drink minuman = (Drink) dataSnapshotIter.getValue (Drink.class);
          minuman.setKey (dataSnapshotIter.getKey());
          drinkArrayList.add (minuman);
        }
        myRecyclerViewAdapter = new AdapterMenuMinuman (drinkArrayList, MembeliMinuman.this);

        myRecyclerView.setAdapter (myRecyclerViewAdapter);
      }

      @Override
      public void onCancelled (@NonNull DatabaseError databaseError)
      {
        System.out.println (databaseError.getDetails() + " " + databaseError.getMessage());
      }
    });

    butPesan.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (MembeliMinuman.this, konfirmasiMembeliMinuman.class));
      }
    });
  }
}
