package com.example.program1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.program1.R;
import com.example.program1.model.ModelTransaksiMinuman;
import java.util.ArrayList;

public class AdapterKonfirmasiTransaksiMinuman extends RecyclerView.Adapter<AdapterKonfirmasiTransaksiMinuman.ViewHolder>
{
  private ArrayList<ModelTransaksiMinuman> drinkArrayList = new ArrayList<>();
  private Context context1;

  public AdapterKonfirmasiTransaksiMinuman (ArrayList<ModelTransaksiMinuman> drinkArrayList, Context context1)
  {
    this.drinkArrayList = drinkArrayList;
    this.context1 = context1;
  }

  class ViewHolder extends RecyclerView.ViewHolder
  {
    TextView name;
    TextView price;
    TextView jumlah;
    TextView total;

    ViewHolder (@NonNull View itemView)
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
    final String name = drinkArrayList.get (position).getNamaMinuman ();
    final String price = String.valueOf (drinkArrayList.get (position).getHargaMinuman ());
    final String jumlah = String.valueOf (drinkArrayList.get (position).getJumlahMinuman ());
    int totalMinuman = Integer.valueOf (jumlah) * Integer.valueOf (price);
    final String total = String.valueOf (totalMinuman);
    viewHolderOperand.name.setText (name);
    viewHolderOperand.price.setText (price);
    viewHolderOperand.jumlah.setText (jumlah);
    viewHolderOperand.total.setText (total);
  }

  @Override
  public int getItemCount () { return drinkArrayList.size (); }
}
