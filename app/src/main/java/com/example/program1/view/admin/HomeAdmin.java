package com.example.program1.view.admin;


import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.program1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

//import

public class HomeAdmin extends AppCompatActivity 
{

    public static String ACTIVE = "fragment_volunteer_aktif";

    MemasukanMakanan memasukanMakanan;

    private Button butMakanan;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        final FragmentTransaction ft = getFragmentManager().beginTransaction();

        butMakanan = findViewById(R.id.btn_membuat_makanan);
        fm = getSupportFragmentManager();

//        butMakanan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fm.beginTransaction().replace(R.id.root_layout_admin, new MemasukanMakanan()).commit();
//            }
//        });


    }


}
