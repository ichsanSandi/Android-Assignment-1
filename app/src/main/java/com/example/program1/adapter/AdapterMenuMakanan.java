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
import com.example.program1.model.Food;
import com.example.program1.R;
import com.example.program1.model.ModelTransaksiMakanan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class AdapterMenuMakanan extends RecyclerView.Adapter<AdapterMenuMakanan.ViewHolder>
{
  private ArrayList<Food> foodArrayList;
  private Context context1;

  public AdapterMenuMakanan (ArrayList<Food> foodArrayList, Context context1)
  {
    this.foodArrayList = foodArrayList;
    this.context1 = context1;
  }

  class ViewHolder extends RecyclerView.ViewHolder
  {
    TextView name;
    TextView price;
    Button orderButton;
    EditText orderAmount;

    ViewHolder (@NonNull View itemView)
    {
      super (itemView);
      name = (TextView) itemView.findViewById (R.id.melihat_nama_makan_minum);
      price = (TextView) itemView.findViewById (R.id.melihat_harga_makan_minum);
      orderButton = (Button) itemView.findViewById (R.id.orderButton);
      orderAmount = (EditText) itemView.findViewById (R.id.orderAmount);
    }
  }

  @NonNull
  public ViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType)
  {
    View view = LayoutInflater.from (viewGroup.getContext ()).inflate (R.layout.item_makan_minum, viewGroup, false);
    return new ViewHolder (view);
  }

  public void onBindViewHolder (final ViewHolder viewHolder, final int position)
  {
    FirebaseAuth auth;
    auth = FirebaseAuth.getInstance ();
    final String name = foodArrayList.get (position).getName ();
    final String price = String.valueOf (foodArrayList.get (position).getPrice ());
    final String uid = "";
    final String emailUser = auth.getCurrentUser ().getEmail ();
    final Button orderButton = viewHolder.orderButton;
    final DatabaseReference dbRef = FirebaseDatabase.getInstance ().getReference ();
    viewHolder.name.setText (name);
    viewHolder.price.setText (price);

    orderButton.setOnClickListener (new View.OnClickListener ()
    {
      @Override
      public void onClick (View v)
      {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder (context1);
        alertDialog.setMessage ("Total Order: " + (Integer.valueOf (price) * Integer.valueOf (viewHolder.orderAmount.getText ().toString ())) );
        AlertDialog alert11 = alertDialog.create ();
        final String jumlah = viewHolder.orderAmount.getText ().toString ();
        final ModelTransaksiMakanan dataMakanan = new ModelTransaksiMakanan (uid, name, price, emailUser, jumlah,"pesan", Integer.MAX_VALUE);
        final DatabaseReference pushId = dbRef.child ("transaksiMakanan");
        DatabaseReference getId = FirebaseDatabase.getInstance ().getReference ().child ("transaksiMakanan");
        getId.push ().setValue (dataMakanan);
        alert11.show ();
      }
    });
  }

  @Override
  public int getItemCount () { return foodArrayList.size ();	}
}
