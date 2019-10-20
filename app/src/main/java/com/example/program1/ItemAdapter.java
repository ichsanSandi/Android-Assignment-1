package com.example.program1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflator;
    String[] foods;
    String[] prices;
    String[] descriptions;

    public ItemAdapter (Context c, String[] foods, String[] prices, String[] descriptions)
    {
        this.foods = foods;
        this.prices = prices;
        this.descriptions = descriptions;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foods.length;
    }

    @Override
    public Object getItem(int i) {
        return foods[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflator.inflate(R.layout.item_layout, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView priceTextView = (TextView) v.findViewById(R.id.priceTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);

        String name = foods[i];
        String price = prices[i];
        String description = descriptions[i];

        nameTextView.setText(name);
        priceTextView.setText(price);
        descriptionTextView.setText(description);
//        View v = mInflator.inflate(R.layout.item_layout, null);
//        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
//        TextView priceTextView = (TextView) v.findViewById(R.id.priceTextView);
//
//        nameTextView.setText(listOfName.get(position));
//        priceTextView.setText(listOfInteger.get(position).toString());

        return v;
    }
}
