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

public class RoomInsertDataUser extends AppCompatActivity
{
  Button tambahUserButton, lihatUserButton;
  TextView firstName, lastName, idUser;
  public static AppDatabase AppDatabase;
  AppDatabase db;

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_room_user_insert_data);
    db = Room.databaseBuilder (getApplicationContext(), AppDatabase.class, "database-name").build();
    tambahUserButton = findViewById (R.id.tambahUserBtn);
    lihatUserButton = findViewById (R.id.lihatUserBtn);
    idUser = findViewById (R.id.idUserTambahText);
    firstName = findViewById (R.id.firstNameTambahText);
    lastName = findViewById (R.id.lastNameTambahText);
    AppDatabase = Room.databaseBuilder (getApplicationContext(),AppDatabase.class,"userdb").allowMainThreadQueries().build();

    tambahUserButton.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        int userId = Integer.parseInt (idUser.getText().toString());
        String first_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        User user = new User();
        user.setUid (userId);
        user.setFirstName (first_name);
        user.setLastName (last_name);
        RoomInsertDataUser.AppDatabase.userDao().addUser (user);
        Toast.makeText (getApplicationContext(),"berhasil boy",Toast.LENGTH_SHORT).show();
      }
    });
    lihatUserButton.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (RoomInsertDataUser.this, RoomReadDataUser.class));
      }
    });
  }
}
