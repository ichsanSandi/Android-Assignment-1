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

import com.example.program1.Food;
import com.example.program1.R;
import com.example.program1.model.ModelMakanan;
import com.example.program1.model.Pengguna;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterMeja extends RecyclerView.Adapter<AdapterMeja.ViewHolder> {

    LayoutInflater mInflator;
    ArrayList<Pengguna> foodArrayList = new ArrayList<>();
    Context c;

    public AdapterMeja (ArrayList<Pengguna> foodArrayList, Context c)
    {
        this.foodArrayList = foodArrayList;
        this.c = c;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        Button meja;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meja = (Button) itemView.findViewById(R.id.btn_meja);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = mInflator.from(viewGroup.getContext()).inflate(R.layout.item_list_meja, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position)
    {
        final String name = foodArrayList.get(position).getNama();


        viewHolder.meja.setText(name);
    }

    @Override
    public int getItemCount()
    {
        return foodArrayList.size();
    }

}
