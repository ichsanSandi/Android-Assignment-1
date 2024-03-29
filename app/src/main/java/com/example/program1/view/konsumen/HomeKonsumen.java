package com.example.program1.view.konsumen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.program1.R;
import com.example.program1.adapter.AdapterKonfirmasiTransaksiMakanan;
import com.example.program1.model.ModelTransaksiMakanan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class HomeKonsumen extends AppCompatActivity
{
  Button butMakanan, butMinuman;
  FirebaseAuth auth;
  DatabaseReference dbRef;
  DatabaseReference databaseReference;
  RecyclerView myRecyclerView;
  RecyclerView.Adapter myRecyclerViewAdapter;
  RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
  ArrayList<ModelTransaksiMakanan> foodArrayList = new ArrayList<>();

  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_home_konsumen);
    auth = FirebaseAuth.getInstance();
    final String emailUser = auth.getCurrentUser().getEmail();
    dbRef = FirebaseDatabase.getInstance().getReference();
    butMakanan = findViewById (R.id.konsumen_beli_makanan);
    butMinuman = findViewById (R.id.konsumen_beli_minuman);

    butMakanan.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (HomeKonsumen.this, MembeliMakanan.class));
      }
    });

    butMinuman.setOnClickListener (new View.OnClickListener()
    {
      @Override
      public void onClick (View v)
      {
        startActivity (new Intent (HomeKonsumen.this, MembeliMinuman.class));
      }
    });

    myRecyclerView = (RecyclerView) findViewById (R.id.transaksi_recyclerView_pesanan);
    myRecyclerView.setHasFixedSize (true);
    myRecyclerViewLayoutMgr = new LinearLayoutManager (this);
    myRecyclerView.setLayoutManager (myRecyclerViewLayoutMgr);
    databaseReference = FirebaseDatabase.getInstance().getReference();
    databaseReference.child ("transaksiMakanan").addValueEventListener (new ValueEventListener()
    {
      @Override
      public void onDataChange (@NonNull DataSnapshot dataSnapshot)
      {
        System.out.println (dataSnapshot.getChildren());
        for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren())
        {
          String user = dataSnapshotIter.getValue (ModelTransaksiMakanan.class).getNamaKonsumen();
          ModelTransaksiMakanan makanan = (ModelTransaksiMakanan) dataSnapshotIter.getValue (ModelTransaksiMakanan.class);
          if (user.equalsIgnoreCase (emailUser))
          {
            if ( (dataSnapshotIter.getValue (ModelTransaksiMakanan.class).getStatusMakanan()).equalsIgnoreCase ("beli") || (dataSnapshotIter.getValue (ModelTransaksiMakanan.class).getStatusMakanan()).equalsIgnoreCase ("bayar"))
            {
              foodArrayList.add (makanan);
            }
          }
        }
        myRecyclerViewAdapter = new AdapterKonfirmasiTransaksiMakanan (foodArrayList, HomeKonsumen.this);
        myRecyclerView.setAdapter (myRecyclerViewAdapter);
      }

      @Override
      public void onCancelled (@NonNull DatabaseError databaseError1)
      {
        System.out.println (databaseError1.getDetails()+" "+databaseError1.getMessage());
      }
    });
  }
}
