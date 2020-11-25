package com.test.food_court;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class admin_page extends AppCompatActivity {

    EditText meal_name, meal_price, meal_image_url;
    Button btn_add;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        firebaseFirestore = FirebaseFirestore.getInstance();
        meal_name = findViewById(R.id.meal_name);
        meal_price = findViewById(R.id.meal_price);
        meal_image_url = findViewById(R.id.meal_url);
        btn_add = findViewById(R.id.btn_add_product);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> product_entry = new HashMap<>();
                product_entry.put("Name", meal_name.getText().toString());
                product_entry.put("Price", meal_price.getText().toString());
                product_entry.put("MealPic", meal_image_url.getText().toString());
                firebaseFirestore.collection("meals")
                        .add(product_entry)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(admin_page.this, "Meal Successfully added", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Error", e.getMessage());
                            }
                        });

            }
        });

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(admin_page.this,MenuList.class));
        finish();
    }
}