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


//        Resources res = getResources();
//        myListView = (ListView) findViewById(R.id.myListView);
//        foods = res.getStringArray(R.array.foods);
//        prices = res.getStringArray(R.array.prices);
//        descriptions = res.getStringArray(R.array.descriptions);




//      ChildEventListener childEventListener = new ChildEventListener() {
//        @Override
//        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
////          Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
//
//          Food food = dataSnapshot.getValue(Food.class);
//          System.out.println(food.getId());
//
//
//        }
//
//        @Override
//        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//        }
//
//        @Override
//        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//        }
//
//        @Override
//        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//      };
//      databaseReference.addChildEventListener(childEventListener);

//        ItemAdapter itemAdapter = new ItemAdapter(this, foods, prices, descriptions);
//        myListView.setAdapter(itemAdapter);

//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent showImage = new Intent(getApplicationContext(), ImageItem.class);
//                showImage.putExtra("com.example.program1.ITEM_INDEX",position);
//                startActivity(showImage);
//            }
//        });

//        myListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, foods));

//        progressTextView = (TextView) findViewById(R.id.progressTextView);
//        thisContext = this;

//        progressTextView.setText("");

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent secondTierIntent = new Intent(getApplicationContext(), AppTierModule.class);
//                startActivity(secondTierIntent);
                onBackPressed();
            }
        });
    }

    public static Intent getActiveIntent(Activity activity)
    {
      return new Intent(activity, SecondTierModule.class);
    }
//
//    public void previousButton(){
//        TextView textView;
//        textView = findViewById(R.id.backButton);
//        Intent backIntent = new Intent(getApplicationContext(), AppTierModule.class);
//        startActivity(backIntent);
//    }
}
