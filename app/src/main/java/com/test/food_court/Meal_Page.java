package com.test.food_court;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Meal_Page extends AppCompatActivity {

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;
    String mealId = "",type,meal_pic,name,price;
    FirebaseFirestore firebaseFirestore;
    Items_list items_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_page);
        firebaseFirestore = FirebaseFirestore.getInstance();
        items_list=new Items_list();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mealId = extras.getString("position");
            type=extras.getString("meal");
            name=extras.getString("name");
            price=extras.getString("price");
            meal_pic=extras.getString("url");
        }
        food_description = findViewById(R.id.food_description);
        food_price = findViewById(R.id.food_price);
        food_price.setText(price);
        food_name = findViewById(R.id.food_name);
        food_name.setText(name);
        food_image = findViewById(R.id.img_food);
        Picasso.get().load(meal_pic).into(food_image);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(R.style.CollapsedAppbar);

        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String price=food_price.getText().toString();
                Double _price=Double.parseDouble(price);
                Double total_price=_price*Double.parseDouble(numberButton.getNumber());

                add_order(food_name.getText().toString(),String.valueOf(total_price));
            }
        });

    }








    public void add_order(String meal_name,String total){
        Map<String, Object> product_entry = new HashMap<>();
        product_entry.put("Name", meal_name);
        product_entry.put("Qty", numberButton.getNumber());
        product_entry.put("Total", total);
         firebaseFirestore.collection("taken_orders").add(product_entry).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                startActivity(new Intent(Meal_Page.this,user_picked_items.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

}






