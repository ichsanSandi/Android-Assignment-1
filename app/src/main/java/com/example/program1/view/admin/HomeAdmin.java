package com.example.program1.view.admin;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.program1.model.Food;
import com.example.program1.R;
import com.google.firebase.database.DatabaseReference;

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
    private CardView butMakanan, butMinuman, butBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        final FragmentTransaction ft = getFragmentManager().beginTransaction();

        butMakanan = findViewById(R.id.btn_memasukan_makanan);
        butMinuman = findViewById(R.id.btn_memasukan_minuman);
        butBayar = findViewById(R.id.btn_bayar_pesanan);


        butMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this, LihatMenuMakanan.class));
            }
        });
        butMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this, LihatMenuMinuman.class));
            }
        });

        butBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this, MemasukanOvoAdmin.class));
            }
        });

    }



}
