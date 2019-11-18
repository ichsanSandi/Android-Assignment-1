package com.example.program1.Room;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Room;
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
public class ExampleJobService extends JobService
{
  private static final String TAG = "ExampleJobService";
  public static AppDatabase AppDatabase;
  ArrayList<Food> foodArrayList;
  FirebaseAuth auth;
  FirebaseUser user;
  DatabaseReference dbRef;
  MJobExecuter mJobExecuter;

  @Override
  public boolean onStartJob(final JobParameters params)
  {
    Log.d(TAG, "Job started");
    mJobExecuter = new MJobExecuter()
    {

      @Override
      protected void onPostExecute(String s) {
        doBackgroundWork(params);
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        jobFinished(params,false);
      }
    };
    mJobExecuter.execute();
    return true;
  }

  private void doBackgroundWork(final JobParameters params)
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
        List<Foods> users = RoomReadDataUser.AppDatabase.foodDao().getAll();
        for (Foods fod : users)
        {
          j++;
        }
        if(i < j)
        {
          Log.d(TAG, "Data DB lokal ada yang kurang");
        }
        else if (i > j)
        {
          Log.d(TAG, "Data DB server ada yang kurang");
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError)
      {
        System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
      }
    });

  }

  @Override
  public boolean onStopJob(JobParameters params)
  {
    Log.d(TAG, "Job cancelled before completion");
    mJobExecuter.cancel(true);
    return false;
  }
}