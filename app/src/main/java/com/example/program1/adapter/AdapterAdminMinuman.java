package com.example.program1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.program1.Drink;
import com.example.program1.Food;
import com.example.program1.R;
import com.example.program1.model.ModelTransaksiMinuman;
import com.example.program1.view.admin.MemasukanMinuman;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterAdminMinuman extends RecyclerView.Adapter<AdapterAdminMinuman.ViewHolder> {

    LayoutInflater mInflator;
    String[] foods;
    String[] prices;
    String[] descriptions;
    ArrayList<Drink> foodArrayList = new ArrayList<>();
    Context c;

    public AdapterAdminMinuman(ArrayList<Drink> foodArrayList, Context c)
    {
        this.foodArrayList = foodArrayList;
        this.c = c;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView price;
        String uid;
        String emailUser;
        String harga;
        Button orderButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.melihat_nama_makan_minum);
            price = (TextView) itemView.findViewById(R.id.melihat_harga_makan_minum);
            orderButton = (Button) itemView.findViewById(R.id.orderButton);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = mInflator.from(viewGroup.getContext()).inflate(R.layout.item_admin_makan_minum, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position)
    {
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        final String name1 = foodArrayList.get(position).getName();
        final String price1 = String.valueOf(foodArrayList.get(position).getPrice());
        final String uid = "";
        final String emailUser = auth.getCurrentUser().getEmail();
        final Button orderButton = viewHolder.orderButton;
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

//      viewHolder.orderAmount.setText(orderAmountText);
        viewHolder.name.setText(name1);
        viewHolder.price.setText(price1);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(c.getApplicationContext(), MemasukanMinuman.class);
                data.putExtra("namaMinuman", name1);
                data.putExtra("hargaMinuman", price1);
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
