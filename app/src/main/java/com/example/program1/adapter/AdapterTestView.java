package com.example.program1.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.program1.R;
import com.example.program1.RoomDB.Foods;
import com.example.program1.RoomDB.FoodsDatabase;
import com.example.program1.UpdateFoodsItemTest;

import java.util.ArrayList;

public class AdapterTestView extends RecyclerView.Adapter<AdapterTestView.ViewHolder> {

  LayoutInflater layoutInflater;
  ArrayList<Foods> foodsList;
  Context c;
  FoodsDatabase foodsDatabase;
  RecyclerView.Adapter recyclerAdapter;

  public AdapterTestView(ArrayList<Foods> foodArrayList, Context c, FoodsDatabase foodsDatabase, RecyclerView.Adapter recyclerViewAdapter)
  {
    this.foodsList = foodArrayList;
    this.c = c;
    this.foodsDatabase = foodsDatabase;
    this.recyclerAdapter =  recyclerViewAdapter;
  }

  class ViewHolder extends RecyclerView.ViewHolder
  {
    TextView name;
    TextView id;
    TextView price;
    Button deleteBttn;
    Button updateBttn;
    ViewHolder(@NonNull View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.nameFoodsText);
      id = (TextView) itemView.findViewById(R.id.idFoodsText);
      price = (TextView) itemView.findViewById(R.id.priceFoodsText);
      deleteBttn = (Button) itemView.findViewById(R.id.deleteItemButton);
      updateBttn = (Button) itemView.findViewById(R.id.updateItemButton);
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = layoutInflater.from(parent.getContext()).inflate(R.layout.item_test_layout, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  public void onBindViewHolder(@NonNull final AdapterTestView.ViewHolder holder, final int position) {
    final String id = String.valueOf(foodsList.get(position).getId());
    final String price = String.valueOf(foodsList.get(position).getPrice());
    final String name = foodsList.get(position).getName();

    holder.price.setText(price);
    holder.id.setText(id);
    holder.name.setText(name);

    Button delBtn = holder.deleteBttn;
    delBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setCancelable(false);
        builder.setMessage("Anda yakin ingin hapus?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            if (foodsList.size() == 1)
            {
              foodsDatabase.FoodsDao().deleteFood(foodsList.get(0));
              foodsList.remove(0);
              AdapterTestView.this.notifyItemRemoved(0);
            }
            else
            {
              foodsDatabase.FoodsDao().deleteFood(foodsList.get(position));
              foodsList.remove(position);
              AdapterTestView.this.notifyItemRemoved(position);
            }
          }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
          }
        });
        AlertDialog alert = builder.create();
        alert.show();
      }
    });

    Button updtBtn = holder.updateBttn;
    updtBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v){
        Intent foodObj = new Intent(c.getApplicationContext(), UpdateFoodsItemTest.class);
        foodObj.putExtra("id", id);
        foodObj.putExtra("name", name);
        foodObj.putExtra("price", price);
        c.startActivity(foodObj);
        AdapterTestView.this.notifyItemChanged(position);
      }
    });
  }
  @Override
  public int getItemCount() {
    return foodsList.size();
  }
}