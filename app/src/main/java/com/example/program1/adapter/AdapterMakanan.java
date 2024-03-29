package com.example.program1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.program1.R;
import com.example.program1.model.ModelMakanan;
import java.util.ArrayList;

public class AdapterMakanan extends RecyclerView.Adapter<AdapterMakanan.ViewHolder>
{
  private TextView name;
  private TextView price;
  private ImageView foto;
  private LinearLayout detail;
  private ArrayList<ModelMakanan> foodArrayList = new ArrayList<>();
  private Context context1;
  private OnItemClickListener listenerDetail;

  public interface OnItemClickListener { void onItemClick (ModelMakanan model); }

  public AdapterMakanan (ArrayList<ModelMakanan> foodArrayList, Context context1, OnItemClickListener listenerDetail)
  {
    this.foodArrayList = foodArrayList;
    this.context1 = context1;
    this.listenerDetail = listenerDetail;
  }

  class ViewHolder extends RecyclerView.ViewHolder
  {
    ViewHolder (@NonNull View itemView)
    {
      super (itemView);
      name = (TextView) itemView.findViewById (R.id.melihat_nama_makan_minum);
      price = (TextView) itemView.findViewById (R.id.melihat_harga_makan_minum);
      foto = itemView.findViewById (R.id.foto_makan_minum);

      detail = itemView.findViewById (R.id.item_selected_makan_minum);
    }

    void bind (final ModelMakanan model, final OnItemClickListener listener)
    {
      final String name1 = model.getNamaMakanan ();
      final String price1 = model.getHargaMakanan ();
      name.setText (name1);
      price.setText (price1);
      detail.setOnClickListener (new View.OnClickListener ()
      {
        @Override
        public void onClick (View view)
        {
          listener.onItemClick (model);
        }
      });
    }
  }

  @NonNull
  public ViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType)
  {
    View view = LayoutInflater.from (viewGroup.getContext ()).inflate (R.layout.item_makan_minum, viewGroup, false);
    return new ViewHolder (view);
  }

  @Override
  public void onBindViewHolder (ViewHolder viewHolder, final int position)
    { viewHolder.bind (foodArrayList.get (position), listenerDetail); }

  @Override
  public int getItemCount () { return foodArrayList.size (); }
}
