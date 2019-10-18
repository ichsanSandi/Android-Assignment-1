package com.example.program1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class SecondTierModule extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layer_module);
    }

    public void previousButton(){
        textView = findViewById(R.id.backButton);
        Intent intent = new Intent(this, AppTierModule.class);
        startActivity(intent);
    }
}
