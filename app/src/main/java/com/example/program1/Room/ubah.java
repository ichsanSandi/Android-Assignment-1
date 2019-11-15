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

public class ubah extends AppCompatActivity {

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
        ubahid = getIntent().getStringExtra("id");
        ubahnama1 = getIntent().getStringExtra("firstName");
        ubahnama2 = getIntent().getStringExtra("lastName");
        id.setText(ubahid);
        nama1.setText(ubahnama1);
        nama2.setText(ubahnama2);

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

                activity_user.AppDatabase.userDao().update(user);
                Toast.makeText(getApplicationContext(),"berhasil boy",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ubah.this, read.class));

            }
        });
        but2.setVisibility(View.GONE);
    }
}
