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

import com.example.program1.DatabaseConn;
import com.example.program1.R;
import com.example.program1.RoomDB.Foods;
import com.example.program1.RoomDB.FoodsDatabase;
import com.example.program1.UpdateFoodsItemTest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterItemTestView extends RecyclerView.Adapter<AdapterItemTestView.ViewHolder>
{
  private ArrayList<Foods> foodsList;
  private Context context1;
  private FoodsDatabase foodsDatabase;
  private RecyclerView.Adapter recyclerAdapter;

  public AdapterItemTestView (ArrayList<Foods> foodArrayList, Context context1, FoodsDatabase foodsDatabase, RecyclerView.Adapter recyclerViewAdapter)
  {
    this.foodsList = foodArrayList;
    this.context1 = context1;
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

    ViewHolder (@NonNull View itemView)
    {
      super (itemView);
      name = (TextView) itemView.findViewById (R.id.nameFoodsText);
      id = (TextView) itemView.findViewById (R.id.idFoodsText);
      price = (TextView) itemView.findViewById (R.id.priceFoodsText);
      deleteBttn = (Button) itemView.findViewById (R.id.deleteItemButton);
      updateBttn = (Button) itemView.findViewById (R.id.updateItemButton);
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
  {
    View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_test_layout, parent, false);
    return new ViewHolder (view);
  }

  public void onBindViewHolder (@NonNull final AdapterItemTestView.ViewHolder holder, final int position)
  {
    final String server_id = foodsList.get (position).getServer_id ();
    final String id = String.valueOf(foodsList.get (position).getId ());
    final String price = String.valueOf (foodsList.get (position).getPrice ());
    final String name = foodsList.get (position).getName ();
    holder.price.setText (price);
    holder.id.setText (server_id);
    holder.name.setText (name);
    Button delBtn = holder.deleteBttn;
    
    delBtn.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View v)
      {
        AlertDialog.Builder builder = new AlertDialog.Builder (context1);
        builder.setCancelable (false);
        builder.setMessage ("Anda yakin ingin hapus?");
        builder.setPositiveButton ("Ya", new DialogInterface.OnClickListener ()
        {
          @Override
          public void onClick (DialogInterface dialog, int which)
          {
            final String id = String.valueOf(foodsList.get(position).getId());
            foodsDatabase.FoodsDao ().deleteFood (foodsList.get (position));
            foodsList.remove (position);
            AdapterItemTestView.this.notifyItemRemoved (position);
            AdapterItemTestView.this.notifyDataSetChanged ();
            new Thread(new Runnable() {
              @Override
              public void run()
              {
                DatabaseConn dbConn = new DatabaseConn();
                try {
                  PreparedStatement statement1 = dbConn.connection1().prepareStatement("DELETE FROM foods" +
                          "WHERE id = (?)");
                  statement1.setString(1, id);
                  statement1.executeQuery();
                  statement1.close();
                  dbConn.connection1().close();
                } catch (SQLException e) {
                  e.printStackTrace();
                }
              }
            }).start();
          }
        });
        builder.setNegativeButton ("Tidak", new DialogInterface.OnClickListener ()
        {
          @Override
          public void onClick (DialogInterface dialog, int which)
          {
            dialog.cancel ();
          }
        });
        AlertDialog alert = builder.create ();
        alert.show ();
      }
    });

    Button updtBtn = holder.updateBttn;
    updtBtn.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View v)
      {
        Intent foodIntent = new Intent (context1.getApplicationContext (), UpdateFoodsItemTest.class);
        foodIntent.putExtra ("id", id);
        foodIntent.putExtra ("name", name);
        foodIntent.putExtra ("price", price);
        foodIntent.putExtra("server_id", server_id);
        context1.startActivity (foodIntent);
        AdapterItemTestView.this.notifyItemChanged (position);
      }
    });
  }
  @Override
  public int getItemCount () { return foodsList.size (); }
}
