package com.example.program1.view.konsumen;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.program1.R;
import com.example.program1.view.HalamanMasuk;

//import

public class HomeKonsumen extends AppCompatActivity
{

    public static String ACTIVE = "fragment_volunteer_aktif";

    MembeliMakanan membeliMakanan;

    private Button butMakanan;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_konsumen);


        final FragmentTransaction ft = getFragmentManager().beginTransaction();

        butMakanan = findViewById(R.id.btn_membuat_makanan);
        fm = getSupportFragmentManager();

    }

    public void beli (View view)
    {
        Intent intent = new Intent(this, MembeliMakanan.class);

        startActivity(intent);

    }
    public void isiOvo (View view)
    {
        Intent intent = new Intent(this, MemasukanOvo.class);

        startActivity(intent);

    }


}
