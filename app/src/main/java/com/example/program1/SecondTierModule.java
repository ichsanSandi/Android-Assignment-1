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
import com.example.program1.model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class SecondTierModule extends AppCompatActivity
{
  DatabaseReference foodDatabaseReference;
  RecyclerView foodRecyclerView;
  RecyclerView.Adapter foodRecyclerViewAdapter;
  RecyclerView.LayoutManager foodRecyclerViewLayoutMgr;
  ArrayList<Food> foodArrayList;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState)
    {
      super.onCreate (savedInstanceState);
      setContentView (R.layout.second_layer_module);
      
      foodRecyclerView = (RecyclerView) findViewById (R.id.foodRecyclerView);
      foodRecyclerView.setHasFixedSize (true);
      foodRecyclerViewLayoutMgr = new LinearLayoutManager (this);
      foodRecyclerView.setLayoutManager (foodRecyclerViewLayoutMgr);
      
      foodDatabaseReference = FirebaseDatabase.getInstance ().getReference ();
      foodDatabaseReference.child ("foods").addValueEventListener (new ValueEventListener ()
      {
        @Override
        public void onDataChange (@NonNull DataSnapshot foodDataSnapshot)
        {
          System.out.println (foodDataSnapshot.getChildren ());
          for (DataSnapshot dataSnapshotIterator : foodDataSnapshot .getChildren ())
          {
            System.out.println (dataSnapshotIterator.getValue ());
            Food foodObject = (Food) dataSnapshotIterator.getValue (Food.class);
            foodObject.setKey (dataSnapshotIterator.getKey ());
            foodArrayList.add (foodObject);
          }
          foodRecyclerViewAdapter = new ItemAdapter (foodArrayList, SecondTierModule.this);
          foodRecyclerView.setAdapter (foodRecyclerViewAdapter);
        }

        @Override
        public void onCancelled (@NonNull DatabaseError databaseError1)
        {
          System.out.println (databaseError1.getDetails () + " " + databaseError1.getMessage ());
        }
      });
      
      Button backButton = (Button) findViewById (R.id.backButton);
      backButton.setOnClickListener (new View.OnClickListener ()
      {
        @Override
        public void onClick (View v)
        {
          onBackPressed ();
        }
      });
    }

    public static Intent getActiveIntent (Activity activity)
    {
      return new Intent (activity, SecondTierModule.class);
    }
}