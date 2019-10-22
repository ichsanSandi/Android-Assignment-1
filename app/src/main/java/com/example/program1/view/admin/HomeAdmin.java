package com.example.program1.view.admin;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.program1.R;
import com.example.program1.view.HalamanMasuk;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import

public class HomeAdmin extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

    }


//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_logout) {
//            logout();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_volunteer);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

//    private void logout() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_volunteer);
//        drawer.closeDrawer(GravityCompat.START);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setMessage("Keluar?");
//        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                auth.signOut();
//                startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
//                HomePengajuan.this.finish();
//            }
//        });
//        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

}
