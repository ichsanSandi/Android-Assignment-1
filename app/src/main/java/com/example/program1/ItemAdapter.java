package com.example.program1;

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
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>
{
	private ArrayList<Food> foodArrayList;
	private Context context1;
	
	ItemAdapter(ArrayList<Food> foodArrayList, Context context1)
	{
		this.foodArrayList = foodArrayList;
		this.context1 = context1;
	}
	
	class ViewHolder extends RecyclerView.ViewHolder
	{
		TextView name;
		TextView price;
		TextView description;
		Button orderButton;
		EditText orderAmount;
		
		ViewHolder (@NonNull View itemView)
		{
			super (itemView);
			name = (TextView) itemView.findViewById (R.id.nameTextView);
			price = (TextView) itemView.findViewById (R.id.priceTextView);
			description = (TextView) itemView.findViewById (R.id.descriptionTextView);
			orderButton = (Button) itemView.findViewById (R.id.orderButton);
			orderAmount = (EditText) itemView.findViewById (R.id.orderAmount);
		}
	}
	
	@NonNull
	public ViewHolder onCreateViewHolder (ViewGroup viewGroup1, int viewType)
	{
		View viewHolderLayout = LayoutInflater.from (viewGroup1.getContext ()).inflate (R.layout.item_layout, viewGroup1, false);
		return new ViewHolder (viewHolderLayout);
	}
	
	public void onBindViewHolder (final ViewHolder viewHolderLayout, final int position)
	{
		final String name = foodArrayList.get (position).getName ();
		final String price = String.valueOf (foodArrayList.get (position).getPrice ());
		final String description = foodArrayList.get (position).getId ();
		final Button orderButton = viewHolderLayout.orderButton;
		final String orderAmountText = viewHolderLayout.orderAmount.getText ().toString ();
		
		viewHolderLayout.orderAmount.setText (orderAmountText);
		viewHolderLayout.name.setText (name);
		viewHolderLayout.description.setText (description);
		viewHolderLayout.price.setText (price);
		
		orderButton.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View v)
			{
				AlertDialog.Builder alertDialog1 = new AlertDialog.Builder (context1);
				alertDialog1.setMessage ("Total Order: " + (Integer.valueOf (price) * Integer.valueOf (viewHolderLayout.orderAmount.getText ().toString ())) );
				AlertDialog alert11 = alertDialog1.create ();
				alert11.show ();
			}
		});
	}
	
	@Override
	public int getItemCount ()
	{
		return foodArrayList.size ();
	}
}