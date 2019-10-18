package com.example.program1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppTierModule extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_tier_module);
    }

    public void sendMessage (View view)
    {
        Intent intent = new Intent(this, SecondTierModule.class);
        textView = findViewById(R.id.testButton);
        startActivity(intent);
        textView.setText("Haha hihi");
    }
}
