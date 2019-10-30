package com.example.program1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.program1.Food;
import com.example.program1.R;
import com.example.program1.model.ModelMakanan;
import com.example.program1.view.konsumen.MembeliMakanan;

import java.util.ArrayList;

public class AdapterMakanan extends RecyclerView.Adapter<AdapterMakanan.ViewHolder> {

    private Context context;
    LayoutInflater mInflator;
    TextView name;
    TextView price;
    ImageView foto;
    LinearLayout detail;
    ArrayList<ModelMakanan> foodArrayList = new ArrayList<>();
    Context c;
    OnItemClickListener listenerDetail;

    public interface OnItemClickListener {
        void onItemClick(ModelMakanan model);
    }
    public AdapterMakanan (ArrayList<ModelMakanan> foodArrayList, Context c, OnItemClickListener listenerDetail)
    {
        this.foodArrayList = foodArrayList;
        this.c = c;
        this.listenerDetail = listenerDetail;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.melihat_nama_makan_minum);
            price = (TextView) itemView.findViewById(R.id.melihat_harga_makan_minum);
            foto = itemView.findViewById(R.id.foto_makan_minum);

            detail = itemView.findViewById(R.id.item_selected_makan_minum);
        }
        public void bind(final ModelMakanan model, final OnItemClickListener listener)
        {
            final String name1 = model.getNamaMakanan();
            final String price1 = model.getHargaMakanan();

//            Glide.with(context).load(model.getFotoMakanan()).into(foto);
            name.setText(name1);
            price.setText(price1);
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(model);
                }
            });

        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = mInflator.from(viewGroup.getContext()).inflate(R.layout.item_makan_minum, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {
        viewHolder.bind(foodArrayList.get(position), listenerDetail);

    }

    @Override
    public int getItemCount()
    {
        return foodArrayList.size();
    }

}
