package com.example.program1.Room;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.program1.R;
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerContoh extends AppCompatActivity
{
    private static final String TAG = "JobSchedulerContoh";
    private static final int JOB_ID = 101;
    private static final long period = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler_service);
    }


    public void scheduleJob(View v)
    {
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        JobInfo.Builder info = new JobInfo.Builder(JOB_ID, componentName);
        info.setRequiresCharging(true);
        info.setPersisted(true);
//        info.setPeriodic(15 * 60 * 1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            info.setMinimumLatency(period);
            android.app.job.JobScheduler scheduler = (android.app.job.JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            JobInfo jobInfo = info.build();
            scheduler.schedule(jobInfo);
        }
        else
        {
            info.setPeriodic(period);
            android.app.job.JobScheduler scheduler = (android.app.job.JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            JobInfo jobInfo = info.build();
            scheduler.schedule(jobInfo);
        }
    }

    public void cancelJob(View v)
    {
        android.app.job.JobScheduler scheduler = (android.app.job.JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(101);

        Log.d(TAG, "Job cancelled");
    }
}
