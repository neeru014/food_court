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


public class Meal_ViewHolder extends RecyclerView.Adapter<Meal_ViewHolder.MViweHolder> {

    Context context;
    ArrayList <Meal> Menu;

    public Meal_ViewHolder(Context c , ArrayList<Meal> m)
    {
        context = c;
        Menu = m;
    }

    @NonNull
    @Override
    public MViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MViweHolder(LayoutInflater.from(context).inflate(R.layout.meals_layout,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MViweHolder holder, final int position) {

        holder.Name.setText(Menu.get(position).getName());
        holder.Price.setText(Menu.get(position).getPrice()+"$");
        Picasso.get().load(Menu.get(position).getMealPic()).into(holder.MealPic);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(context, Meal_Page.class);


                 intent.putExtra("position","0"+String.valueOf(position+1));
                intent.putExtra("name",Menu.get(position).getName());
                intent.putExtra("price",Menu.get(position).getPrice());
                 intent.putExtra("url",Menu.get(position).getMealPic());
                context.startActivity(intent);

             }
        });
    }

    @Override
    public int getItemCount() {
        return Menu.size();
    }

    class MViweHolder extends RecyclerView.ViewHolder
    {
        TextView Name;
        TextView Price;
        ImageView MealPic;
        CardView cardView;
        public MViweHolder(View itemView)
        {
            super(itemView);
            Name  = (TextView) itemView.findViewById(R.id.food_name);
            Price = (TextView) itemView.findViewById(R.id.food_price);
            MealPic = (ImageView) itemView.findViewById(R.id.food_image);
            cardView = (CardView)itemView.findViewById(R.id.CardView);

        }

    }


}