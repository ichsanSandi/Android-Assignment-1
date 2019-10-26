package com.example.program1.view.konsumen;

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

import com.example.program1.Food;
import com.example.program1.ItemAdapter;
import com.example.program1.R;
import com.example.program1.adapter.AdapterMakanan;
import com.example.program1.model.ModelMakanan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MembeliMakanan extends AppCompatActivity {


    DatabaseReference databaseReference;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
    ArrayList<ModelMakanan> foodArrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membeli_makanan);

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
                    ModelMakanan makanan = (ModelMakanan) dataSnapshotIter.getValue(ModelMakanan.class);
                    makanan.setKey(dataSnapshotIter.getKey());
                    foodArrayList.add(makanan);
                }
                myRecyclerViewAdapter = new AdapterMakanan(foodArrayList, MembeliMakanan.this, new AdapterMakanan.OnItemClickListener() {
                    @Override
                    public void onItemClick(ModelMakanan model) {
                        Intent data = new Intent(getApplicationContext(), MembeliMakanan.class);
                        data.putExtra("namaMakanan", model.getNamaMakanan());
                        data.putExtra("hargaMakanan", model.getHargaMakanan());
                        getApplicationContext().startActivity(data);
                    }
                });
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

//
    }

    public static Intent getActiveIntent(Activity activity)
    {
        return new Intent(activity, MembeliMakanan.class);
    }
}
