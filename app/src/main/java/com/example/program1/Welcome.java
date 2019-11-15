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
import androidx.room.Room;

import com.example.program1.Room.Foods;
import com.example.program1.Room.FoodsDatabase;
import com.example.program1.Room.User;
import com.example.program1.Room.activity_user;
import com.example.program1.Room.read;
import com.example.program1.model.Drink;
import com.example.program1.model.Food;
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
import com.example.program1.Room.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import com.example.program1.view.Perusahaan.HomePerusahaan;

public class Welcome extends AppCompatActivity {

    /**

     **/

    private static final String TAG = Welcome.class.getSimpleName();

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference dbRef;
    public static AppDatabase AppDatabase;
    ArrayList<String> PenggunaList;
    ArrayList<Food> foodArrayList;


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
                            startActivity(new Intent(Welcome.this, activity_user.class));
//                            startActivity(new Intent(getApplicationContext(),coba.class));
                        } else {
                            if (!get_internet(getApplicationContext())) {
                                Toast.makeText(Welcome.this, "Tidak ada koneksi internet", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), activity_user.class));
//                                startActivity(new Intent(getApplicationContext(),coba.class));
                            } else {

                                AppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"userdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();

                                //food
                                dbRef.child("drinks").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        foodArrayList = new ArrayList<>();
                                        System.out.println(dataSnapshot.getChildren());
                                        for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren()) {
                                            Food makanan = dataSnapshotIter.getValue(Food.class);
                                            foodArrayList.add(makanan);
                                            Foods food = new Foods();
                                            System.out.println(dataSnapshotIter.getValue(Drink.class).getId() + "lah");
                                            food.setUidFood(dataSnapshotIter.getValue(Drink.class).getId());
                                            food.setNameFood(dataSnapshotIter.getValue(Drink.class).getName());
                                            food.setPriceFood(String.valueOf(dataSnapshotIter.getValue(Drink.class).getPrice()));
                                            Welcome.AppDatabase.foodDao().update(food);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                                    }
                                });

                                //user
//                                List<User> users = Welcome.AppDatabase.userDao().getAll();
//
//                                dbRef.child("pengguna").addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        PenggunaList = new ArrayList<>();
//                                        int i = 0;
//                                        for (DataSnapshot child : dataSnapshot.getChildren()) {
//                                            String peng = child.getKey();
//                                            PenggunaList.add(peng);
//
//                                            System.out.println("kenapa2" + PenggunaList.get(i));
//                                            i++;
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                                        Toast.makeText(Welcome.this, "Gagal Login / Sesi Berakhir", Toast.LENGTH_LONG).show();
//                                        Log.w(TAG, "Login ERROR : " + databaseError.getDetails());
//                                        startActivity(new Intent(getApplicationContext(), activity_user.class));
//                                    }
//                                });
//                                int position = 0;
//
//                                for(User usr : users)
//                                {
//                                    if("" != (String.valueOf(usr.uid)))
//                                    {
//                                        Pengguna daftar = new Pengguna((String.valueOf(usr.uid)), usr.firstName, usr.lastName, Pengguna.Konsumen,"123456","0");
//                                        dbRef.child("pengguna").child((String.valueOf(usr.uid))).push().setValue(daftar);
//                                        System.out.println("kenapa2");
//                                    }
//                                    System.out.println("kenapa1");
//                                    position++;
//                                }

                                if (user != null) {
                                    dbRef.child("pengguna").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                Pengguna pen = child.getValue(Pengguna.class);
                                                cekLevel(pen.getLevel());
                                            }
                                            startActivity(new Intent(getApplicationContext(), activity_user.class));
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Toast.makeText(Welcome.this, "Gagal Login / Sesi Berakhir", Toast.LENGTH_LONG).show();
                                            Log.w(TAG, "Login ERROR : " + databaseError.getDetails());
                                            startActivity(new Intent(getApplicationContext(), activity_user.class));
                                        }
                                    });
                                } else {

                                    startActivity(new Intent(getApplicationContext(), activity_user.class));
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
