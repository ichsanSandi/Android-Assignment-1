package com.example.program1.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.program1.R;
import com.google.android.gms.internal.firebase_auth.zzdb;

public class activity_user extends AppCompatActivity {

    Button but1, but2;
    TextView nama1, nama2, id;
    public static AppDatabase AppDatabase;
    Context context;
    String ubahid = "", ubahnama1 = "",ubahnama2 = "";
    boolean status = false;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        id = findViewById(R.id.id);
        nama1 = findViewById(R.id.nama1);
        nama2 = findViewById(R.id.nama2);

        AppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"userdb").allowMainThreadQueries().build();

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = Integer.parseInt(id.getText().toString());
                String first_name = nama1.getText().toString();
                String last_name = nama2.getText().toString();
                User user = new User();
                user.setUid(userId);
                user.setFirstName(first_name);
                user.setLastName(last_name);
                activity_user.AppDatabase.userDao().addUser(user);
                Toast.makeText(getApplicationContext(),"berhasil boy",Toast.LENGTH_SHORT).show();
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_user.this, read.class));
            }
        });
    }
}
