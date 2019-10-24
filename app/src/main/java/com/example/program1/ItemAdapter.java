package com.example.program1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    LayoutInflater mInflator;
    String[] foods;
    String[] prices;
    String[] descriptions;
    ArrayList<Food> foodArrayList = new ArrayList<>();
    Context c;

    public ItemAdapter (ArrayList<Food> foodArrayList, Context c)
    {
      this.foodArrayList = foodArrayList;
      this.c = c;
    }

//    public ItemAdapter (Context c, String[] foods, String[] prices, String[] descriptions)
//    {
//        this.foods = foods;
//        this.prices = prices;
//        this.descriptions = descriptions;
//        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
      TextView name;
      TextView price;
      TextView description;

      public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.nameTextView);
        price = (TextView) itemView.findViewById(R.id.priceTextView);
        description = (TextView) itemView.findViewById(R.id.descriptionTextView);
      }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
      View view = mInflator.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
      ViewHolder viewHolder = new ViewHolder(view);
      return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {
      final String name = foodArrayList.get(position).getName();
      final String price = String.valueOf(foodArrayList.get(position).getPrice());
      final String description = String.valueOf(foodArrayList.get(position).getId());

      viewHolder.name.setText(name);
      viewHolder.description.setText(description);
      viewHolder.price.setText(price);

    }

    @Override
    public int getItemCount()
    {
      return foodArrayList.size();
    }

//  @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        View v = mInflator.inflate(R.layout.item_layout, null);
//        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
//        TextView priceTextView = (TextView) v.findViewById(R.id.priceTextView);
//        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);
//
//        String name = foods[i];
//        String price = prices[i];
//        String description = descriptions[i];
//
//        nameTextView.setText(name);
//        priceTextView.setText(price);
//        descriptionTextView.setText(description);
////        View v = mInflator.inflate(R.layout.item_layout, null);
////        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
////        TextView priceTextView = (TextView) v.findViewById(R.id.priceTextView);
////
////        nameTextView.setText(listOfName.get(position));
////        priceTextView.setText(listOfInteger.get(position).toString());
//
//        return v;
//    }
}
