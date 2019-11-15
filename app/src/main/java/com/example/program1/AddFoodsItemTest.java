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


public class AddFoodsItemTest extends AppCompatActivity {
  Button addBttn;
  EditText nameEditText;
  EditText priceEditText;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test_activity_add_foods);

    nameEditText = (EditText) findViewById(R.id.foodsAddNameEditText);
    priceEditText = (EditText) findViewById(R.id.foodsAddPriceEditText);
    addBttn = (Button) findViewById(R.id.foodsAddConfirmButton);

    addBttn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Foods foods = new Foods();
        foods.setName(nameEditText.getText().toString());
        foods.setPrice(Integer.valueOf(priceEditText.getText().toString()));
        FoodsDatabase foodDb = Room.databaseBuilder(getApplicationContext(), FoodsDatabase.class, "foods-database")
                .allowMainThreadQueries()
                .build();
        foodDb.FoodsDao().insertFood(foods);
        Context context = getApplicationContext();
        CharSequence text = "Data Added";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent returnIntent = new Intent(AddFoodsItemTest.this, TestActivity.class);
        startActivity(returnIntent);
      }
    });
  }

  @Override
  public void onBackPressed() {
    Intent returnIntent = new Intent(AddFoodsItemTest.this, TestActivity.class);
    startActivity(returnIntent);
  }
}
