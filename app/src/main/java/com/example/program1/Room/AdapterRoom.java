package com.example.program1.Room;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.program1.R;

import java.util.ArrayList;

public class AdapterRoom extends RecyclerView.Adapter<AdapterRoom.ViewHolder>
{

  LayoutInflater mInflator;
  ArrayList<Foods> foodArrayList = new ArrayList<>();
  Context c;

  public AdapterRoom(ArrayList<Foods> foodArrayList, Context c)
  {
    this.foodArrayList = foodArrayList;
    this.c = c;
  }

  class ViewHolder extends RecyclerView.ViewHolder
  {
    TextView id;
    TextView name;
    TextView price;
    Button ubahBut;
    Button hapusBut;

    public ViewHolder(@NonNull View itemView)
    {
      super(itemView);
      id = (TextView) itemView.findViewById(R.id.id);
      name = (TextView) itemView.findViewById(R.id.nama1);
      price = (TextView) itemView.findViewById(R.id.nama2);
      ubahBut = (Button) itemView.findViewById(R.id.ubahcoy);
      hapusBut = (Button) itemView.findViewById(R.id.hapuscoy);
    }
  }

  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
  {
    View view = mInflator.from(viewGroup.getContext()).inflate(R.layout.item_room, viewGroup, false);
    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  public void onBindViewHolder(final ViewHolder viewHolder, final int position)
  {
    final String uid = foodArrayList.get(position).getUidFood();
    final String name1 = foodArrayList.get(position).getNameFood();
    final String name2 = foodArrayList.get(position).getPriceFood();

    final Button ubahBut = viewHolder.ubahBut;
    final Button hapusBut = viewHolder.hapusBut;

    viewHolder.id.setText(String.valueOf(uid));
    viewHolder.name.setText(name1);
    viewHolder.price.setText(name2);

    ubahBut.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Intent data = new Intent(c.getApplicationContext(), RoomUpdateDataUser.class);
        data.putExtra("id", String.valueOf(uid));
        data.putExtra("firstName", name1);
        data.putExtra("lastName", name2);
        c.getApplicationContext().startActivity(data);
      }
    });
    hapusBut.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        String id = uid;
        Foods user = new Foods();
        user.setUidFood(id);
        RoomReadDataUser.AppDatabase.foodDao().delete(user);

        Intent data = new Intent(c.getApplicationContext(), RoomReadDataUser.class);
        c.getApplicationContext().startActivity(data);
      }
    });
  }

  @Override
  public int getItemCount()
  {
    return foodArrayList.size();
  }

}
