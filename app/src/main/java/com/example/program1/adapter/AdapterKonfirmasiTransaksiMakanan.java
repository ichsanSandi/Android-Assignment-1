package com.example.program1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.program1.R;
import com.example.program1.model.ModelTransaksiMakanan;
import java.util.ArrayList;

public class AdapterKonfirmasiTransaksiMakanan extends RecyclerView.Adapter<AdapterKonfirmasiTransaksiMakanan.ViewHolder>
{
  private ArrayList<ModelTransaksiMakanan> foodArrayList;
  private Context c;

  public AdapterKonfirmasiTransaksiMakanan (ArrayList<ModelTransaksiMakanan> foodArrayList, Context c)
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

    ViewHolder(@NonNull View itemView)
    {
      super (itemView);
      name = (TextView) itemView.findViewById (R.id.transaksi_nama_makan_minum);
      price = (TextView) itemView.findViewById (R.id.transaksi_harga_makan_minum);
      jumlah = (TextView) itemView.findViewById (R.id.transaksi_jumlah_makan_minum);
      total = (TextView) itemView.findViewById (R.id.transaksi_total_makan_minum);
    }
  }

  @NonNull
  public ViewHolder onCreateViewHolder (ViewGroup viewGroupOperand, int viewType)
  {
    View view1 = LayoutInflater.from (viewGroupOperand.getContext ()).inflate (R.layout.item_transaksi, viewGroupOperand, false);
    return new ViewHolder (view1);
  }

  public void onBindViewHolder (final ViewHolder viewHolderOperand, final int position)
  {
    final String name = foodArrayList.get (position).getNamaMakanan ();
    final String price = String.valueOf (foodArrayList.get (position).getHargaMakanan ());
    final String jumlah = String.valueOf (foodArrayList.get (position).getJumlahMakanan ());
    int totalMakanan = Integer.valueOf (jumlah) * Integer.valueOf (price);
    final String total = String.valueOf (totalMakanan);

    viewHolderOperand.name.setText (name);
    viewHolderOperand.price.setText (price);
    viewHolderOperand.jumlah.setText (jumlah);
    viewHolderOperand.total.setText (total);

    System.out.println("berapa ");
  }

  @Override
  public int getItemCount ()
  { return foodArrayList.size (); }
}
