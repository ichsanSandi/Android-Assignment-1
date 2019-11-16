package com.example.program1.adapter;

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
import com.example.program1.model.Drink;
import com.example.program1.view.admin.MemasukanMinuman;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class AdapterAdminMinuman extends RecyclerView.Adapter<AdapterAdminMinuman.ViewHolder>
{
	private ArrayList<Drink> foodArrayList;
	private Context c;
	
	public AdapterAdminMinuman (ArrayList<Drink> foodArrayList, Context c)
	{
		this.foodArrayList = foodArrayList;
		this.c = c;
	}
	
	class ViewHolder extends RecyclerView.ViewHolder
	{
		TextView name;
		TextView price;
		Button orderButton;
		
		ViewHolder (@NonNull View itemView)
		{
			super (itemView);
			name = (TextView) itemView.findViewById (R.id.melihat_nama_makan_minum);
			price = (TextView) itemView.findViewById (R.id.melihat_harga_makan_minum);
			orderButton = (Button) itemView.findViewById (R.id.orderButton);
		}
	}
	
	@NonNull
	public ViewHolder onCreateViewHolder (ViewGroup viewGroupOperand, int viewType)
	{
		View view1 = LayoutInflater.from (viewGroupOperand.getContext ()).inflate (R.layout.item_admin_makan_minum, viewGroupOperand, false);
		return new ViewHolder (view1);
	}
	
	public void onBindViewHolder (final ViewHolder viewHolderOperand, final int position)
	{
		FirebaseAuth auth;
		auth = FirebaseAuth.getInstance ();
		final String name = foodArrayList.get (position).getName ();
		final String price = String.valueOf (foodArrayList.get (position).getPrice ());
		final String uid = "";
		final String emailUser = auth.getCurrentUser ().getEmail ();
		final Button orderButton = viewHolderOperand.orderButton;
		final DatabaseReference dbRef = FirebaseDatabase.getInstance ().getReference ();

//      viewHolder.orderAmount.setText(orderAmountText);
		viewHolderOperand.name.setText (name);
		viewHolderOperand.price.setText (price);
		
		orderButton.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				Intent data = new Intent (c.getApplicationContext (), MemasukanMinuman.class);
				data.putExtra ("namaMinuman", name);
				data.putExtra ("hargaMinuman", price);
				c.getApplicationContext ().startActivity (data);
			}
		});
	}
	
	@Override
	public int getItemCount ()
		{ return foodArrayList.size (); }
}
