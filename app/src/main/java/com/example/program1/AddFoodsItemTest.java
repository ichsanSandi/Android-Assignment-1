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

public class AddFoodsItemTest extends AppCompatActivity
{
  Button addBttn;
  EditText nameEditText;
  EditText priceEditText;

  @Override
  protected void onCreate (@Nullable Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.test_activity_add_foods);

    nameEditText = (EditText) findViewById (R.id.foodsAddNameEditText);
    priceEditText = (EditText) findViewById (R.id.foodsAddPriceEditText);
    addBttn = (Button) findViewById (R.id.foodsAddConfirmButton);

    nameEditText.setHint ("Nama Makanan");
    priceEditText.setHint ("Harga Makanan");

    addBttn.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View v)
      {
        Foods foodsObject = new Foods ();
        foodsObject.setName (nameEditText.getText ().toString ());
        foodsObject.setPrice(Integer.valueOf (priceEditText.getText ().toString ()));
        
        FoodsDatabase foodDb = Room.databaseBuilder (getApplicationContext (), FoodsDatabase.class, "foods-database")
                .allowMainThreadQueries ()
                .addMigrations (MIGRATION_1_2)
                .build ();
        foodDb.FoodsDao ().insertFood (foodsObject);

        Context context1 = getApplicationContext ();
        CharSequence appearedToastText = "Data Added";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText (context1, appearedToastText, duration);
        toast.show ();
        
        Intent returnIntent = new Intent (AddFoodsItemTest.this, TestActivity.class);
        startActivity (returnIntent);
      }
    });
  }

  @Override
  public void onBackPressed ()
  {
    Intent returnIntent = new Intent (AddFoodsItemTest.this, TestActivity.class);
    startActivity (returnIntent);
  }
}
