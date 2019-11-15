package com.example.program1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.program1.Room.activity_user;


public class AppTierModule extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.presentation_tier_module);
    }

    public void sendMessage (View view)
    {
        TextView textView;
//        Intent intent = new Intent(this, SecondTierModule.class);
        textView = findViewById(R.id.testButton);
        startActivity(SecondTierModule.getActiveIntent(AppTierModule.this));

        EditText name = (EditText) findViewById(R.id.nameText);
    }
    public void loginAkun (View view)
    {
        Intent intent = new Intent(this, activity_user.class);

        startActivity(intent);

    }
}
