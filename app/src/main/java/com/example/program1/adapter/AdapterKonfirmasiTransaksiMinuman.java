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
import com.example.program1.model.ModelTransaksiMinuman;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterKonfirmasiTransaksiMinuman extends RecyclerView.Adapter<AdapterKonfirmasiTransaksiMinuman.ViewHolder> {

    LayoutInflater mInflator;
    ArrayList<ModelTransaksiMinuman> drinkArrayList = new ArrayList<>();
    Context c;

    public AdapterKonfirmasiTransaksiMinuman(ArrayList<ModelTransaksiMinuman> drinkArrayList, Context c)
    {
        this.drinkArrayList = drinkArrayList;
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
        final String name = drinkArrayList.get(position).getNamaMinuman();
        final String price = String.valueOf(drinkArrayList.get(position).getHargaMinuman());
        final String jumlah = String.valueOf(drinkArrayList.get(position).getJumlahMinuman());
        int totalMinuman = Integer.valueOf(jumlah) * Integer.valueOf(price);
        final String total = String.valueOf(totalMinuman);
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        final Button orderButton = viewHolder.orderButton;
        final String uid = "";
        final String emailUser = auth.getCurrentUser().getEmail();

        viewHolder.name.setText(name);
        viewHolder.price.setText(price);
        viewHolder.jumlah.setText(jumlah);
        viewHolder.total.setText(total);
    }

    @Override
    public int getItemCount()
    {
        return drinkArrayList.size();
    }

}
