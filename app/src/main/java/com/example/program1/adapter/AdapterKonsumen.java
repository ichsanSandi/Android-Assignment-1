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

import com.example.program1.Food;
import com.example.program1.R;
import com.example.program1.model.ModelMakanan;
import com.example.program1.model.ModelTransaksiMakanan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterKonsumen extends RecyclerView.Adapter<AdapterKonsumen.ViewHolder> {

    LayoutInflater mInflator;
    String[] foods;
    String[] prices;
    String[] descriptions;
    ArrayList<Food> foodArrayList = new ArrayList<>();
    Context c;

    public AdapterKonsumen (ArrayList<Food> foodArrayList, Context c)
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
        System.out.println(emailUser + "email");

//      viewHolder.orderAmount.setText(orderAmountText);
        viewHolder.name.setText(name);
        viewHolder.price.setText(price);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(c);
                alertDialog.setMessage("Total Order: " + (Integer.valueOf(price) * Integer.valueOf(viewHolder.orderAmount.getText().toString())) );
                AlertDialog alert11 = alertDialog.create();
                String jumlah = viewHolder.orderAmount.getText().toString();
                ModelTransaksiMakanan dataMakanan = new ModelTransaksiMakanan(uid, name, price, emailUser, jumlah,"pesan" );
                DatabaseReference pushId = dbRef.child("transaksiMakanan");
                pushId.push().setValue(dataMakanan);

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
