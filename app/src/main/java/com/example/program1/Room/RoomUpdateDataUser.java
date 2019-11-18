package com.example.program1.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.program1.R;

public class RoomUpdateDataUser extends AppCompatActivity {

  Button ubahUserButton, lihatUserButton;
  TextView firstNameUbah, lastNameUbah, idUser;
  public static AppDatabase AppDatabase;
  String ubahIdUser = "", ubahFirstName = "",ubahLastname = "";

  AppDatabase db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_room_user_update_data);
    db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
    ubahUserButton = findViewById(R.id.ubahUserBtn);
    lihatUserButton = findViewById(R.id.lihatUserBtn);
    idUser = findViewById(R.id.idUserUbahText);
    firstNameUbah = findViewById(R.id.firstNameUbahText);
    lastNameUbah = findViewById(R.id.lastNameUbahText);
    ubahIdUser = getIntent().getStringExtra("id");
    ubahFirstName = getIntent().getStringExtra("firstName");
    ubahLastname = getIntent().getStringExtra("lastName");
    idUser.setText(ubahIdUser);
    firstNameUbah.setText(ubahFirstName);
    lastNameUbah.setText(ubahLastname);

    AppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"userdb").allowMainThreadQueries().build();

    ubahUserButton.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        int userId = Integer.parseInt(idUser.getText().toString());
        String first_name = firstNameUbah.getText().toString();
        String last_name = lastNameUbah.getText().toString();
        User user = new User();
        user.setUid(userId);
        user.setFirstName(first_name);
        user.setLastName(last_name);

        RoomUpdateDataUser.AppDatabase.userDao().update(user);
        Toast.makeText(getApplicationContext(),"berhasil boy",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RoomUpdateDataUser.this, RoomReadDataUser.class));

      }
    });
    lihatUserButton.setVisibility(View.GONE);
  }
}
