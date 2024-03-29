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
import com.example.program1.model.Food;
import com.example.program1.R;
import com.example.program1.adapter.AdapterMenuMakanan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MembeliMakanan extends AppCompatActivity
{
  DatabaseReference databaseReference;
  RecyclerView myRecyclerView;
  RecyclerView.Adapter myRecyclerViewAdapter;
  RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
  ArrayList<Food> foodArrayList;
  Button butPesan;

  @Override
  protected void onCreate (@Nullable Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_membeli_makanan);
    myRecyclerView = (RecyclerView) findViewById (R.id.myFoodRecyclerView);
    myRecyclerView.setHasFixedSize (true);
    myRecyclerViewLayoutMgr = new LinearLayoutManager (this);
    myRecyclerView.setLayoutManager (myRecyclerViewLayoutMgr);
    butPesan = findViewById (R.id.btn_pesan_makanan);
    databaseReference = FirebaseDatabase.getInstance().getReference();
    databaseReference.child ("foods").addValueEventListener (new ValueEventListener()
    {
      @Override
      public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
        foodArrayList = new ArrayList<>();
        for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren())
        {
          Food makanan = (Food) dataSnapshotIter.getValue (Food.class);
          makanan.setKey (dataSnapshotIter.getKey());
          foodArrayList.add (makanan);
        }
        myRecyclerViewAdapter = new AdapterMenuMakanan (foodArrayList, MembeliMakanan.this);
        myRecyclerView.setAdapter (myRecyclerViewAdapter);
      }

      @Override
      public void onCancelled (@NonNull DatabaseError databaseError)
      {
        System.out.println (databaseError.getDetails()+" "+databaseError.getMessage());
      }
    });

    butPesan.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (MembeliMakanan.this, konfirmasiMembeliMakanan.class));
      }
    });
  }
}
