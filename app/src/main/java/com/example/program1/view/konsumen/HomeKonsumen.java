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
import com.example.program1.adapter.AdapterTransaksiMakanan;
import com.example.program1.model.ModelTransaksiMakanan;
import com.example.program1.model.ModelTransaksiMinuman;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import

public class HomeKonsumen extends AppCompatActivity
{
    private Button butMakanan, butMinuman;
    private FragmentManager fm;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference dbRef;

    DatabaseReference databaseReference;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
    ArrayList<ModelTransaksiMakanan> foodArrayList = new ArrayList<>();
    ArrayList<ModelTransaksiMinuman> drinkArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_konsumen);
        auth = FirebaseAuth.getInstance();
        final String emailUser = auth.getCurrentUser().getEmail();
        dbRef = FirebaseDatabase.getInstance().getReference();

        butMakanan = findViewById(R.id.konsumen_beli_makanan);
        butMinuman = findViewById(R.id.konsumen_beli_minuman);
        fm = getSupportFragmentManager();
        butMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeKonsumen.this, MembeliMakanan.class));
            }
        });
        butMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeKonsumen.this, MembeliMinuman.class));
            }
        });

        myRecyclerView = (RecyclerView) findViewById(R.id.transaksi_recyclerView_pesanan);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerViewLayoutMgr = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myRecyclerViewLayoutMgr);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("transaksiMakanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getChildren());
                for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren()) {
                    String user = dataSnapshotIter.getValue(ModelTransaksiMakanan.class).getNamaKonsumen();
                    ModelTransaksiMakanan makanan = (ModelTransaksiMakanan) dataSnapshotIter.getValue(ModelTransaksiMakanan.class);

                    if (user.equalsIgnoreCase(emailUser)) {
                        if ((dataSnapshotIter.getValue(ModelTransaksiMakanan.class).getStatusMakanan()).equalsIgnoreCase("beli") || (dataSnapshotIter.getValue(ModelTransaksiMakanan.class).getStatusMakanan()).equalsIgnoreCase("bayar")) {
                            foodArrayList.add(makanan);
                        }
                    }
                }
//                myRecyclerViewAdapter = new AdapterMakanan(foodArrayList, MembeliMakanan.this, new AdapterMakanan.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(ModelMakanan model) {
//                        Intent data = new Intent(getApplicationContext(), MembeliMakanan2.class);
//                        data.putExtra("namaMakanan", model.getNamaMakanan());
//                        data.putExtra("hargaMakanan", model.getHargaMakanan());
//                        getApplicationContext().startActivity(data);
//                    }
//                });
                myRecyclerViewAdapter = new AdapterTransaksiMakanan(foodArrayList, HomeKonsumen.this);

                myRecyclerView.setAdapter(myRecyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });


    }
}
