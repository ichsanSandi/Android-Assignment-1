package com.example.program1.Room;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.example.program1.Welcome;
import com.example.program1.model.Drink;
import com.example.program1.model.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;
    public static AppDatabase AppDatabase;
    ArrayList<Food> foodArrayList;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference dbRef;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");

        System.out.println("run" + 1);
        doBackgroundWork(params);

        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    Log.d(TAG, "run: " + i);

                    if(i%15 == 0)
                    {
                        auth = FirebaseAuth.getInstance();
                        user = auth.getCurrentUser();
                        dbRef = FirebaseDatabase.getInstance().getReference();
                        AppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"userdb").allowMainThreadQueries().fallbackToDestructiveMigration().build();

                        //food
                        dbRef.child("foods").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                foodArrayList = new ArrayList<>();
                                int i = 0;
                                int j = 0;
                                System.out.println(dataSnapshot.getChildren());
                                for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren()) {
                                    i++;

                                }
                                List<Foods> users = read.AppDatabase.foodDao().getAll();
                                for (Foods fod : users)
                                {
                                    j++;
                                }
                                if(i < j || i > j)
                                {
                                    Log.d(TAG, "Data Kurang Atau Lebih");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                            }
                        });
                    }
                    if (jobCancelled) {
                        return;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "Job finished");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    jobFinished(params, false);
                }
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}