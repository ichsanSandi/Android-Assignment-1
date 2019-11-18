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
import com.example.program1.Room.AppDatabase;
import com.example.program1.Room.Foods;
import com.example.program1.Room.RoomInsertDataUser;
import com.example.program1.model.Drink;
import com.example.program1.model.Food;
import com.example.program1.model.Pengguna;
import com.example.program1.view.admin.HomeAdmin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Welcome extends AppCompatActivity
{
  private static final String TAG = Welcome.class.getSimpleName ();

  FirebaseAuth firebaseAuth1;
  FirebaseUser firebaseUser1;
  DatabaseReference firebaseDbRef;
  public static AppDatabase roomAppDatabase;
  ArrayList<String> PenggunaArrayList;
  ArrayList<Food> foodArrayList;

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_welcome);
    getWindow ().setFlags (WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    firebaseAuth1 = FirebaseAuth.getInstance ();
    firebaseUser1 = firebaseAuth1.getCurrentUser ();
    firebaseDbRef = FirebaseDatabase.getInstance ().getReference ();

    new Handler ().postDelayed (new Runnable ()
    {
      @Override
      public void run ()
      {
        ProgressBar progressBar1 = findViewById (R.id.loading_welcome);
        progressBar1.setVisibility (View.VISIBLE);

        new Handler ().postDelayed (new Runnable ()
        {
          @Override
          public void run ()
          {
            SharedPreferences sharedPreferences1 = getApplicationContext ().getSharedPreferences ("com.example.program1", 0);
            if (sharedPreferences1.getBoolean ("first_launch", false))
            {
              sharedPreferences1.edit ().putBoolean ("first_lauch", false).commit ();
              startActivity (new Intent (Welcome.this, RoomInsertDataUser.class));
//                            startActivity(new Intent(getApplicationContext(),coba.class));
            }
            else
            {
              if (!get_internet(getApplicationContext ()))
              {
                Toast.makeText (Welcome.this, "Tidak ada koneksi internet", Toast.LENGTH_LONG).show ();
                startActivity (new Intent (getApplicationContext (), RoomInsertDataUser.class));
//                                startActivity(new Intent(getApplicationContext(),coba.class));
              }
              else
              {
                roomAppDatabase = Room.databaseBuilder (getApplicationContext (),AppDatabase.class,"userdb")
                        .allowMainThreadQueries ()
                        .fallbackToDestructiveMigration ()
                        .build ();

                //food
                firebaseDbRef.child ("drinks").addValueEventListener (new ValueEventListener ()
                {
                  @Override
                  public void onDataChange (@NonNull DataSnapshot dataSnapshot)
                  {
                    foodArrayList = new ArrayList<> ();
                    System.out.println (dataSnapshot.getChildren ());
                    for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren ())
                    {
                      Food makanan = dataSnapshotIter.getValue (Food.class);
                      foodArrayList.add (makanan);
                      Foods foodObject = new Foods ();
                      System.out.println (dataSnapshotIter.getValue (Drink.class).getId () + "lah");
                      foodObject.setUidFood (dataSnapshotIter.getValue (Drink.class).getId ());
                      foodObject.setNameFood (dataSnapshotIter.getValue(Drink.class).getName ());
                      foodObject.setPriceFood (String.valueOf (dataSnapshotIter.getValue (Drink.class).getPrice ()));
                      Welcome.roomAppDatabase.foodDao ().update (foodObject);
                    }
                  }

                  @Override
                  public void onCancelled (@NonNull DatabaseError databaseError1)
                  {
                    System.out.println (databaseError1.getDetails () + " " + databaseError1.getMessage ());
                  }
                });


                if (firebaseUser1 != null)
                {
                  firebaseDbRef.child ("pengguna").child (firebaseUser1.getUid ()).addListenerForSingleValueEvent (new ValueEventListener ()
                  {
                    @Override
                    public void onDataChange (@NonNull DataSnapshot dataSnapshot)
                    {
                      for (DataSnapshot child : dataSnapshot.getChildren ())
                      {
                        Pengguna pengguna1 = child.getValue (Pengguna.class);
                        cekLevel (pengguna1.getLevel ());
                      }
                      startActivity (new Intent (getApplicationContext (), RoomInsertDataUser.class));
                    }

                    @Override
                    public void onCancelled (@NonNull DatabaseError databaseError)
                    {
                      Toast.makeText (Welcome.this, "Gagal Login / Sesi Berakhir", Toast.LENGTH_LONG).show ();
                      Log.w (TAG, "Login ERROR : " + databaseError.getDetails ());
                      startActivity (new Intent(getApplicationContext (), RoomInsertDataUser.class));
                    }
                  });
                }
                else
                {
                  startActivity (new Intent (getApplicationContext (), RoomInsertDataUser.class));
                }
              }
            }
          }
        }, 2000);
      }
    }, 200);
  }

  @Override
  public void onBackPressed ()
  {

  }

  public static boolean get_internet (Context context)
  {
    ConnectivityManager connectivityManager1 = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo1 = connectivityManager1.getActiveNetworkInfo ();

    if (networkInfo1 != null && networkInfo1.isConnectedOrConnecting ())
    {
      android.net.NetworkInfo wifiNetwork = connectivityManager1.getNetworkInfo (ConnectivityManager.TYPE_WIFI);
      android.net.NetworkInfo dataNetwork = connectivityManager1.getNetworkInfo (ConnectivityManager.TYPE_MOBILE);
      return wifiNetwork != null && wifiNetwork.isConnectedOrConnecting () || dataNetwork != null && dataNetwork.isConnectedOrConnecting ();
    }
    else
    {
      return false;
    }
  }

  private void cekLevel (String level)
  {
    switch (level)
    {
      case Pengguna.Admin:
        startActivity (new Intent (Welcome.this, HomeAdmin.class));
        break;
      default:
        Toast.makeText (Welcome.this, "Tidak Diketahui", Toast.LENGTH_LONG).show ();
        Log.w (TAG, "Level Tidak Diketahui");
    }
//        View context = findViewById(R.id.root_layout_masuk);
//        Snackbar.make(context, "Masuk Sebagai " + level, Snackbar.LENGTH_LONG).show();
    Log.d (TAG, "Berhasil Masuk");
  }
}