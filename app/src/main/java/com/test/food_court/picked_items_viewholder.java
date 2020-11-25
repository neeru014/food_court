package com.test.food_court;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;


public class picked_items_viewholder extends RecyclerView.Adapter<picked_items_viewholder.MViweHolder> {

    Context context;
    ArrayList <Items_list> list;

    public picked_items_viewholder(Context c , ArrayList<Items_list> m)
    {
        context = c;
        list = m;
    }

    @NonNull
    @Override
    public MViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MViweHolder(LayoutInflater.from(context).inflate(R.layout.picked_list,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MViweHolder holder, final int position) {

        holder.Name.setText(list.get(position).getName());
        holder.Qty.setText(list.get(position).getQty());
        holder.Total.setText(list.get(position).getTotal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MViweHolder extends RecyclerView.ViewHolder
    {
        TextView Name;
        TextView Qty;
        TextView Total;
        CardView cardView;
        public MViweHolder(View itemView)
        {
            super(itemView);
            Name  = (TextView) itemView.findViewById(R.id.txt_food_name);
            Qty = (TextView) itemView.findViewById(R.id.txt_food_qty);
            Total = (TextView) itemView.findViewById(R.id.txt_food_total);
            cardView = (CardView)itemView.findViewById(R.id.CardView);

        }

    }


}