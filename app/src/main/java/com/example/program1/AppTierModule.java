package com.example.program1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.program1.view.HalamanMasuk;

public class AppTierModule extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_tier_module);
    }

    public void sendMessage (View view)
    {
        TextView textView;
        Intent intent = new Intent(this, SecondTierModule.class);
        textView = findViewById(R.id.testButton);
        startActivity(intent);

        EditText name = (EditText) findViewById(R.id.nameText);
    }
    public void loginAkun (View view)
    {
        Intent intent = new Intent(this, HalamanMasuk.class);

        startActivity(intent);

    }
}
