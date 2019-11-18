package com.example.program1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.program1.R;
import com.example.program1.model.Pengguna;
import java.util.ArrayList;

public class AdapterMeja extends RecyclerView.Adapter<AdapterMeja.ViewHolder>
{
  private ArrayList<Pengguna> foodArrayList;
  private Context c;

  public AdapterMeja (ArrayList<Pengguna> foodArrayList, Context c)
  {
    this.foodArrayList = foodArrayList;
    this.c = c;
  }

  class ViewHolder extends RecyclerView.ViewHolder
  {
    Button meja;
    ViewHolder (@NonNull View itemView)
    {
      super (itemView);
      meja = (Button) itemView.findViewById (R.id.btn_meja);
    }
  }

  @NonNull
  public ViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType)
  {
    View view = LayoutInflater.from (viewGroup.getContext ()).inflate (R.layout.item_list_meja, viewGroup, false);
    return new ViewHolder (view);
  }

  public void onBindViewHolder (final ViewHolder viewHolder, final int position)
  {
    final String name = foodArrayList.get (position).getNama ();
    viewHolder.meja.setText (name);
  }

  @Override
  public int getItemCount ()
    { return foodArrayList.size (); }
}
