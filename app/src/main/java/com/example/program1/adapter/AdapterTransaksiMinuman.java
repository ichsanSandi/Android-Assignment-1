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
import com.example.program1.model.ModelMinuman;
import com.example.program1.model.ModelTransaksiMinuman;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterTransaksiMinuman extends RecyclerView.Adapter<AdapterTransaksiMinuman.ViewHolder> {

    LayoutInflater mInflator;
    ArrayList<ModelTransaksiMinuman> foodArrayList = new ArrayList<>();
    Context c;

    public AdapterTransaksiMinuman (ArrayList<ModelTransaksiMinuman> foodArrayList, Context c)
    {
        this.foodArrayList = foodArrayList;
        this.c = c;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        TextView jumlah;
        TextView total;

        String uid;
        String emailUser;
        String harga;
        Button orderButton;
        EditText orderAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.transaksi_nama_makan_minum);
            price = (TextView) itemView.findViewById(R.id.transaksi_harga_makan_minum);
            jumlah = (TextView) itemView.findViewById(R.id.transaksi_jumlah_makan_minum);
            total = (TextView) itemView.findViewById(R.id.transaksi_total_makan_minum);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = mInflator.from(viewGroup.getContext()).inflate(R.layout.item_transaksi, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position)
    {
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        final String name = foodArrayList.get(position).getNamaMinuman();
        final String price = String.valueOf(foodArrayList.get(position).getHargaMinuman());
        final String jumlah = String.valueOf(foodArrayList.get(position).getJumlahMinuman());
        int totalMinuman = Integer.valueOf(jumlah) * Integer.valueOf(price);
        final String total = String.valueOf(totalMinuman);


        viewHolder.name.setText(name);
        viewHolder.price.setText(price);
        viewHolder.jumlah.setText(jumlah);
        viewHolder.total.setText(total);

        System.out.println("berapa ");
    }

    @Override
    public int getItemCount()
    {
        return foodArrayList.size();
    }

}
