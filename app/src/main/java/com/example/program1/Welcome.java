package com.example.program1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.program1.model.Pengguna;
import com.example.program1.view.HalamanMasuk;
import com.example.program1.view.admin.HomeAdmin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.internal.Util;

//import com.example.program1.view.Perusahaan.HomePerusahaan;

public class Welcome extends AppCompatActivity {

    /**

     **/

    private static final String TAG = Welcome.class.getSimpleName();

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressBar prog = findViewById(R.id.loading_welcome);
                prog.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("com.example.program1", 0);
                        if (pref.getBoolean("first_launch", false)) {
                            pref.edit().putBoolean("first_lauch", false).commit();
                            startActivity(new Intent(Welcome.this, HalamanAwal.class));
//                            startActivity(new Intent(getApplicationContext(),coba.class));
                        } else {
                            if (!get_internet(getApplicationContext())) {
                                Toast.makeText(Welcome.this, "Tidak ada koneksi internet", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),HalamanMasuk.class));
//                                startActivity(new Intent(getApplicationContext(),coba.class));
                            } else {
                                if (user != null) {
                                    dbRef.child("pengguna").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                Pengguna pen = child.getValue(Pengguna.class);
                                                cekLevel(pen.getLevel());
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Toast.makeText(Welcome.this, "Gagal Login / Sesi Berakhir", Toast.LENGTH_LONG).show();
                                            Log.w(TAG, "Login ERROR : " + databaseError.getDetails());
                                            startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                                        }
                                    });
                                } else {
                                    startActivity(new Intent(getApplicationContext(), HalamanMasuk.class));
                                }
                            }


                        }
                    }
                }, 2000);
            }
        }, 200);

    }


    @Override
    public void onBackPressed() {

    }
    public static boolean get_internet(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo data = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return wifi != null && wifi.isConnectedOrConnecting() || data != null && data.isConnectedOrConnecting();
        } else {
            return false;
        }
    }

    private void cekLevel(String level) {
        switch (level) {
            case Pengguna.Admin:
                startActivity(new Intent(Welcome.this, HomeAdmin.class));
                break;


            default:
                Toast.makeText(Welcome.this, "Tidak Diketahui", Toast.LENGTH_LONG).show();
                Log.w(TAG, "Level Tidak Diketahui");
        }
//        View context = findViewById(R.id.root_layout_masuk);
//        Snackbar.make(context, "Masuk Sebagai " + level, Snackbar.LENGTH_LONG).show();
        Log.d(TAG, "Berhasil Masuk");
    }
}
