package com.example.program1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecondTierModule extends AppCompatActivity {
//
//    ItemAdapter itemAdapter;
//    Context thisContext;
//    ListView myListView;
//    String[] foods;
//    String[] prices;
//    String[] descriptions;

  DatabaseReference databaseReference;
  RecyclerView myRecyclerView;
  RecyclerView.Adapter myRecyclerViewAdapter;
  RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
  ArrayList<Food> foodArrayList;
//    TextView progressTextView;
//    Map<String,Integer> foodMap = new LinkedHashMap<String, Integer>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layer_module);

        myRecyclerView = (RecyclerView) findViewById(R.id.foodRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerViewLayoutMgr = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myRecyclerViewLayoutMgr);

      databaseReference = FirebaseDatabase.getInstance().getReference();
      databaseReference.child("foods").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          foodArrayList = new ArrayList<>();
          System.out.println(dataSnapshot.getChildren());
          for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren())
          {
            System.out.println(dataSnapshotIter.getValue());
            Food food = (Food) dataSnapshotIter.getValue(Food.class);
            food.setKey(dataSnapshotIter.getKey());
            foodArrayList.add(food);
          }
          myRecyclerViewAdapter = new ItemAdapter(foodArrayList, SecondTierModule.this);
          myRecyclerView.setAdapter(myRecyclerViewAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
          System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
        }
      });

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static Intent getActiveIntent(Activity activity)
    {
      return new Intent(activity, SecondTierModule.class);
    }
}
