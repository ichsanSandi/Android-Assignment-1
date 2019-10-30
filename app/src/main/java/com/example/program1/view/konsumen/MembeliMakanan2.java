package com.example.program1.view.konsumen;

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

import com.example.program1.Food;
import com.example.program1.ItemAdapter;
import com.example.program1.R;
import com.example.program1.adapter.AdapterKonsumen;
import com.example.program1.adapter.AdapterMakanan;
import com.example.program1.Food;
import com.example.program1.adapter.AdapterTransaksiMakanan;
import com.example.program1.model.ModelTransaksiMakanan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MembeliMakanan2 extends AppCompatActivity {

    FirebaseAuth auth;

    DatabaseReference databaseReference;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
    ArrayList<ModelTransaksiMakanan> foodArrayList;
    Button but_pesan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_makanan);
        auth = FirebaseAuth.getInstance();
        final String emailUser = auth.getCurrentUser().getEmail();

        myRecyclerView = (RecyclerView) findViewById(R.id.transaksi_recyclerView_makanan);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerViewLayoutMgr = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myRecyclerViewLayoutMgr);
        but_pesan = findViewById(R.id.btn_pesan_makanan);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("transaksiMakanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodArrayList = new ArrayList<>();
                System.out.println(dataSnapshot.getChildren());
                for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren()) {
                    String user = dataSnapshotIter.getValue(ModelTransaksiMakanan.class).getNamaKonsumen();
                    ModelTransaksiMakanan makanan = dataSnapshotIter.getValue(ModelTransaksiMakanan.class);
                    if (user.equalsIgnoreCase(emailUser)) {
                        System.out.println( makanan.getHargaMakanan() + "cekcoy");
                        System.out.println(dataSnapshotIter.getValue());
                        foodArrayList.add(makanan);

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
                myRecyclerViewAdapter = new AdapterTransaksiMakanan(foodArrayList, MembeliMakanan2.this);

                myRecyclerView.setAdapter(myRecyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        but_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MembeliMakanan2.this, MembeliMakanan2.class));
            }
        });


//
    }

    public static Intent getActiveIntent(Activity activity)
    {
        return new Intent(activity, MembeliMakanan.class);
    }
}
