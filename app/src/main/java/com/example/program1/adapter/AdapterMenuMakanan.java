package com.example.program1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.program1.model.Food;
import com.example.program1.R;
import com.example.program1.model.ModelTransaksiMakanan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterMenuMakanan extends RecyclerView.Adapter<AdapterMenuMakanan.ViewHolder> {

    LayoutInflater mInflator;
    String[] foods;
    String[] prices;
    String[] descriptions;
    ArrayList<Food> foodArrayList = new ArrayList<>();
    Context c;

    public AdapterMenuMakanan(ArrayList<Food> foodArrayList, Context c)
    {
        this.foodArrayList = foodArrayList;
        this.c = c;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        String uid;
        String emailUser;
        String harga;
        Button orderButton;
        EditText orderAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.melihat_nama_makan_minum);
            price = (TextView) itemView.findViewById(R.id.melihat_harga_makan_minum);
            orderButton = (Button) itemView.findViewById(R.id.orderButton);
            orderAmount = (EditText) itemView.findViewById(R.id.orderAmount);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = mInflator.from(viewGroup.getContext()).inflate(R.layout.item_makan_minum, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position)
    {
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        final String name = foodArrayList.get(position).getName();
        final String price = String.valueOf(foodArrayList.get(position).getPrice());
        final String uid = "";
        final String emailUser = auth.getCurrentUser().getEmail();
        final Button orderButton = viewHolder.orderButton;
        final String orderAmountText = viewHolder.orderAmount.getText().toString();
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

//      viewHolder.orderAmount.setText(orderAmountText);
        viewHolder.name.setText(name);
        viewHolder.price.setText(price);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(c);
                alertDialog.setMessage("Total Order: " + (Integer.valueOf(price) * Integer.valueOf(viewHolder.orderAmount.getText().toString())) );
                AlertDialog alert11 = alertDialog.create();
                final String jumlah = viewHolder.orderAmount.getText().toString();
                final ModelTransaksiMakanan dataMakanan = new ModelTransaksiMakanan(uid, name, price, emailUser, jumlah,"pesan", Integer.MAX_VALUE);
                final DatabaseReference pushId = dbRef.child("transaksiMakanan");
//                final Boolean[] ada = new Boolean[1];
//                ada[0] = true;
                DatabaseReference getId = FirebaseDatabase.getInstance().getReference().child("transaksiMakanan");
                getId.push().setValue(dataMakanan);
//                getId.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot perData : dataSnapshot.getChildren()) {
//                            ModelTransaksiMakanan model = perData.getValue(ModelTransaksiMakanan.class);
//                            if (model.getStatusMakanan() != null && model.getNamaKonsumen().equalsIgnoreCase(emailUser) &&
//                                    model.getStatusMakanan().equalsIgnoreCase("pesan")&& model.getNamaMakanan().equalsIgnoreCase(name))
//                            {
//                                String key = perData.getKey();
//                                System.out.println(key + "key");
//                                pushId.child(key).child("jumlahMakanan").setValue(jumlah);
//                                System.out.println("merubah nilai");
////                                ada[0] = true;
//                                break;
//                            }
//                            else
//                            {
//                                ada[0] = false;
//                                dbRef.child("transaksiMakanan").push().setValue(dataMakanan);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                if (ada[0] == false)
//                {
//                    pushId.push().setValue(dataMakanan);
//                    System.out.println("membuat baru");
//                }

                alert11.show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return foodArrayList.size();
    }

}
