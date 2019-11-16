package com.example.program1.Room;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.program1.R;

public class JobSchedulerService extends AppCompatActivity {
    private static final String TAG = "JobSchedulerService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler_service);
    }

    public void scheduleJob(View v) {
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        JobInfo info = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            info = new JobInfo.Builder(123, componentName)
                    .setRequiresCharging(true)
                    .setPersisted(true)
                    .setPeriodic(15 * 60 * 1000)
                    .build();
            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            int resultCode = scheduler.schedule(info);
            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "Job scheduled");
            } else {
                Log.d(TAG, "Job scheduling failed");
            }
        }

    }

    public void cancelJob(View v) {
        JobScheduler scheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.cancel(123);
        }
        Log.d(TAG, "Job cancelled");
    }
}
