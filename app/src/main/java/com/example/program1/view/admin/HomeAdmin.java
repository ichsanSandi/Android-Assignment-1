package com.example.program1.view.admin;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.program1.Food;
import com.example.program1.ItemAdapter;
import com.example.program1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import

public class HomeAdmin extends AppCompatActivity 
{

    public static String ACTIVE = "fragment_volunteer_aktif";

    MemasukanMakanan memasukanMakanan;
    DatabaseReference databaseReference;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
    ArrayList<Food> foodArrayList;
    private Button butMakanan, butMinuman, butOvo;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        final FragmentTransaction ft = getFragmentManager().beginTransaction();

        butMakanan = findViewById(R.id.btn_memasukan_makanan);
        butMinuman = findViewById(R.id.btn_memasukan_minuman);
        butOvo = findViewById(R.id.btn_memasukan_poin);
        fm = getSupportFragmentManager();

        myRecyclerView = (RecyclerView) findViewById(R.id.foodRecyclerViewAdmin);
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
                myRecyclerViewAdapter = new ItemAdapter(foodArrayList, HomeAdmin.this);
                myRecyclerView.setAdapter(myRecyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
        butMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this, MemasukanMakanan.class));
            }
        });
        butMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this, MemasukanMinuman.class));
            }
        });
        butOvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this, MemasukanOvoAdmin.class));
            }
        });


    }



}
