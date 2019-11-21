package com.example.program1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.program1.RoomDB.Foods;
import com.example.program1.RoomDB.FoodsDatabase;

import static com.example.program1.RoomDB.FoodsDatabase.MIGRATION_1_2;

public class UpdateFoodsItemTest extends AppCompatActivity
{
  Button updtBttn;
  EditText nameEditText;
  EditText priceEditText;
  
  @Override
  protected void onCreate (@Nullable Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.test_activity_update_foods);

    nameEditText = (EditText) findViewById (R.id.foodsUpdateNameEditText);
    priceEditText = (EditText) findViewById (R.id.foodsUpdatePriceEditText);
    updtBttn = (Button) findViewById (R.id.UpdateConfirmButton);

    nameEditText.setText (getIntent ().getStringExtra("name"));
    priceEditText.setHint (getIntent ().getStringExtra ("price"));

    updtBttn.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View v)
      {
        Foods foodsObject = new Foods ();

        foodsObject.setId (Integer.valueOf (getIntent ().getStringExtra ("id")));
        foodsObject.setName (nameEditText.getText ().toString ());
        foodsObject.setPrice (Integer.valueOf (priceEditText.getText ().toString ()));

        FoodsDatabase foodDb =
        Room.databaseBuilder (getApplicationContext (), FoodsDatabase.class, "foods-database")
        .allowMainThreadQueries ()
        .addMigrations (MIGRATION_1_2)
        .build ();

        foodDb.FoodsDao ().updateFood (foodsObject);
        Context toastContext = getApplicationContext ();
        CharSequence toastText = "Data Updated";
        int toastDuration = Toast.LENGTH_LONG;

        Toast toast1 = Toast.makeText (toastContext, toastText, toastDuration);
        toast1.show ();

        Intent returnIntent = new Intent (UpdateFoodsItemTest.this, TestActivity.class);
        startActivity (returnIntent);
      }
    });
  }
  @Override
  public void onBackPressed ()
  {
    Intent returnIntent = new Intent (UpdateFoodsItemTest.this, TestActivity.class);
    startActivity (returnIntent);
  }
}