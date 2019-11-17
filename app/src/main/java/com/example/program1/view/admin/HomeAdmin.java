package com.example.program1.view.admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.program1.R;
import com.example.program1.view.HalamanDaftar;

public class HomeAdmin extends AppCompatActivity
{
  private CardView butMakanan, butMinuman, butBayar, butDaftar;

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_admin);

    butMakanan = findViewById (R.id.btn_memasukan_makanan);
    butMinuman = findViewById (R.id.btn_memasukan_minuman);
    butBayar = findViewById (R.id.btn_bayar_pesanan);
    butDaftar = findViewById (R.id.btn_tambah_meja);

    butMakanan.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (HomeAdmin.this, LihatMenuMakanan.class));
      }
    });
    butMinuman.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (HomeAdmin.this, LihatMenuMinuman.class));
      }
    });

    butBayar.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (HomeAdmin.this, BayarPesanan.class));
      }
    });

    butDaftar.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (HomeAdmin.this, HalamanDaftar.class));
      }
    });
  }
}
