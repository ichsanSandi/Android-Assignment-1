package com.example.program1.view.konsumen;

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
import com.example.program1.adapter.AdapterKonfirmasiTransaksiMinuman;
import com.example.program1.model.ModelTransaksiMinuman;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class konfirmasiMembeliMinuman extends AppCompatActivity
{

     FirebaseAuth auth;
     FirebaseUser user;
     DatabaseReference dbRef;
    DatabaseReference databaseReference;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
    ArrayList<ModelTransaksiMinuman> drinkArrayList;
    Button but_pesan;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) 
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_transaksi_minuman);
        auth = FirebaseAuth.getInstance();
        final String emailUser = auth.getCurrentUser().getEmail();
        dbRef = FirebaseDatabase.getInstance().getReference();
        myRecyclerView = (RecyclerView) findViewById (R.id.transaksi_recyclerView_minuman);
        myRecyclerView.setHasFixedSize (true);
        myRecyclerViewLayoutMgr = new LinearLayoutManager (this);
        myRecyclerView.setLayoutManager (myRecyclerViewLayoutMgr);
        but_pesan = findViewById (R.id.btn_beli_minuman);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child ("transaksiMinuman").addValueEventListener (new ValueEventListener()
        {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) 
            {
                drinkArrayList = new ArrayList<>();
                System.out.println (dataSnapshot.getChildren());
                for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren())
                {
                    String user = dataSnapshotIter.getValue (ModelTransaksiMinuman.class).getNamaKonsumen();
                    ModelTransaksiMinuman minuman = dataSnapshotIter.getValue (ModelTransaksiMinuman.class);
                    if (user.equalsIgnoreCase (emailUser)) 
                    {
                        if ( (dataSnapshotIter.getValue (ModelTransaksiMinuman.class).getStatusMinuman()).equalsIgnoreCase ("pesan")) 
                        {
                                drinkArrayList.add (minuman);
                        }
                    }
                }
                myRecyclerViewAdapter = new AdapterKonfirmasiTransaksiMinuman (drinkArrayList, konfirmasiMembeliMinuman.this);

                myRecyclerView.setAdapter (myRecyclerViewAdapter);
            }

            @Override
            public void onCancelled (@NonNull DatabaseError databaseError) 
            {
                System.out.println (databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        but_pesan.setOnClickListener (new View.OnClickListener() 
        {
            @Override
            public void onClick (View v) 
            {
                DatabaseReference getId = FirebaseDatabase.getInstance().getReference().child ("transaksiMinuman");
                final DatabaseReference pushId = dbRef.child ("transaksiMinuman");
                getId.addValueEventListener (new ValueEventListener() 
                {
                    @Override
                    public void onDataChange (@NonNull DataSnapshot dataSnapshot) 
                    {
                        for (DataSnapshot perData : dataSnapshot.getChildren()) 
                        {
                            ModelTransaksiMinuman model = perData.getValue (ModelTransaksiMinuman.class);
                            if (model.getStatusMinuman() != null) 
                            {
                                if (model.getNamaKonsumen().equalsIgnoreCase (emailUser))
                                {
                                    if (model.getStatusMinuman().equalsIgnoreCase ("pesan"))
                                    {
                                        System.out.println (model.getNamaMinuman());
                                        String key = perData.getKey();
                                        pushId.child (key).child ("statusMinuman").setValue ("beli");
//                                        pushId.child (key).child ("timestamp").setValue (date.);
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled (@NonNull DatabaseError databaseError) 
                    {

                    }
                });
                startActivity (new Intent (konfirmasiMembeliMinuman.this, HomeKonsumen.class));
            }
        });
    }
}
