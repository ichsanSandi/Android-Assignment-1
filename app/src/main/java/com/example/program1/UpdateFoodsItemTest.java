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

public class UpdateFoodsItemTest extends AppCompatActivity {
  Button updtBttn;
  EditText nameEditText;
  EditText priceEditText;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test_activity_update_foods);

    nameEditText = (EditText) findViewById(R.id.foodsUpdateNameEditText);
    priceEditText = (EditText) findViewById(R.id.foodsUpdatePriceEditText);
    updtBttn = (Button) findViewById(R.id.UpdateConfirmButton);

    updtBttn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Foods foods = new Foods();

        foods.setId(Integer.valueOf(getIntent().getStringExtra("id")));
        foods.setName(nameEditText.getText().toString());
        foods.setPrice(Integer.valueOf(priceEditText.getText().toString()));

        nameEditText.setHint(foods.getName());
        priceEditText.setHint(foods.getPrice());

        FoodsDatabase foodDb = Room.databaseBuilder(getApplicationContext(), FoodsDatabase.class, "foods-database")
                .allowMainThreadQueries()
                .build();

        foodDb.FoodsDao().updateFood(foods);
        Context context = getApplicationContext();
        CharSequence text = "Data Updated";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent returnIntent = new Intent(UpdateFoodsItemTest.this, TestActivity.class);
        startActivity(returnIntent);
      }
    });
  }
  @Override
  public void onBackPressed() {
    Intent returnIntent = new Intent(UpdateFoodsItemTest.this, TestActivity.class);
    startActivity(returnIntent);
  }
}