package com.example.program1.view.koki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.program1.R;
import com.example.program1.adapter.AdapterMeja;
import com.example.program1.adapter.AdapterKonfirmasiTransaksiMakanan;
import com.example.program1.model.ModelTransaksiMakanan;
import com.example.program1.model.Pengguna;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeKoki extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference dbRef;

    DatabaseReference databaseReference;
    RecyclerView myRecyclerView;
    RecyclerView myRecyclerView2;
    RecyclerView.Adapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
    RecyclerView.LayoutManager myRecyclerViewLayoutMgr2;
    ArrayList<ModelTransaksiMakanan> foodArrayList;
    ArrayList<Pengguna> foodArrayList2;
    Button but_pesan;
    String uid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_koki);
        auth = FirebaseAuth.getInstance();
        final String emailUser = auth.getCurrentUser().getEmail();
        dbRef = FirebaseDatabase.getInstance().getReference();

        myRecyclerView = (RecyclerView) findViewById(R.id.transaksi_recyclerView_makanan);
        myRecyclerView2 = (RecyclerView) findViewById(R.id.transaksi_recyclerView_meja);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView2.setHasFixedSize(true);
        myRecyclerViewLayoutMgr = new LinearLayoutManager(this);
        myRecyclerViewLayoutMgr2 = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myRecyclerViewLayoutMgr);
        myRecyclerView2.setLayoutManager(myRecyclerViewLayoutMgr2);
        but_pesan = findViewById(R.id.btn_beli_makanan);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("transaksiMakanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodArrayList = new ArrayList<>();
                System.out.println(dataSnapshot.getChildren());
                for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren()) {
                    String user = dataSnapshotIter.getValue(ModelTransaksiMakanan.class).getNamaKonsumen();
                    ModelTransaksiMakanan makanan = dataSnapshotIter.getValue(ModelTransaksiMakanan.class);
                    if((dataSnapshotIter.getValue(ModelTransaksiMakanan.class).getStatusMakanan()).equalsIgnoreCase("beli")) {
                        foodArrayList.add(makanan);

                    }
                }
//                myRecyclerViewAdapter = new AdapterMakanan(foodArrayList, MembeliMakanan.this, new AdapterMakanan.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(ModelMakanan model) {
//                        Intent data = new Intent(getApplicationContext(), konfirmasiMembeliMakanan.class);
//                        data.putExtra("namaMakanan", model.getNamaMakanan());
//                        data.putExtra("hargaMakanan", model.getHargaMakanan());
//                        getApplicationContext().startActivity(data);
//                    }
//                });
                myRecyclerViewAdapter = new AdapterKonfirmasiTransaksiMakanan(foodArrayList, HomeKoki.this);

                myRecyclerView.setAdapter(myRecyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("pengguna").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodArrayList = new ArrayList<>();
                System.out.println(dataSnapshot.getChildren());
                for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren()) {
                    String user = dataSnapshotIter.getValue(ModelTransaksiMakanan.class).getNamaKonsumen();
                    Pengguna makanan = dataSnapshotIter.getValue(Pengguna.class);
                    if((dataSnapshotIter.getValue(Pengguna.class).getLevel()).equalsIgnoreCase("konsumen")) {
                        foodArrayList2.add(makanan);

                    }
                }
                myRecyclerViewAdapter = new AdapterMeja(foodArrayList2, HomeKoki.this);

                myRecyclerView2.setAdapter(myRecyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        but_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference getId = FirebaseDatabase.getInstance().getReference().child("transaksiMakanan");
                final DatabaseReference pushId = dbRef.child("transaksiMakanan");
                getId.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot perData : dataSnapshot.getChildren()) {
                            ModelTransaksiMakanan model = perData.getValue(ModelTransaksiMakanan.class);
                            if (model.getStatusMakanan() != null) {
                                if (model.getStatusMakanan().equalsIgnoreCase("beli"))
                                {
                                    System.out.println(model.getNamaMakanan());
                                    String key = perData.getKey();
                                    pushId.child(key).child("statusMakanan").setValue("bayar");

                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                startActivity(new Intent(HomeKoki.this, HomeKoki.class));
            }
        });


//
    }

    public static Intent getActiveIntent(Activity activity)
    {
        return new Intent(activity, HomeKoki.class);
    }
}
