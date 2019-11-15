package com.example.program1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.program1.R;
import com.example.program1.model.ModelTransaksiMakanan;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdapterKonfirmasiTransaksiMakanan extends RecyclerView.Adapter<AdapterKonfirmasiTransaksiMakanan.ViewHolder> {

    LayoutInflater mInflator;
    ArrayList<ModelTransaksiMakanan> foodArrayList = new ArrayList<>();
    Context c;

    public AdapterKonfirmasiTransaksiMakanan(ArrayList<ModelTransaksiMakanan> foodArrayList, Context c)
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
        final String name = foodArrayList.get(position).getNamaMakanan();
        final String price = String.valueOf(foodArrayList.get(position).getHargaMakanan());
        final String jumlah = String.valueOf(foodArrayList.get(position).getJumlahMakanan());
        int totalMakanan = Integer.valueOf(jumlah) * Integer.valueOf(price);
        final String total = String.valueOf(totalMakanan);


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
