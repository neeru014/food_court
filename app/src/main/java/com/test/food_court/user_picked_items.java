package com.test.food_court;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class user_picked_items extends AppCompatActivity {


    ProgressDialog progressDialog;
    TextView txt_total;
    ArrayList<Items_list> list = new ArrayList<>();
    RecyclerView recyclerView;
    double total=0;
    RecyclerView.Adapter adapter ;
    FirebaseFirestore db;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_picked_items);

        db = FirebaseFirestore.getInstance();
        btn_confirm=findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("taken_orders").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                db.collection("taken_orders")
                                        .document(document.getId()).delete();
                            }
                            finish();
                            startActivity(new Intent(user_picked_items.this,MenuList.class));
                            Toast.makeText(user_picked_items.this,"Order  COnfirmed",Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("ALi", "Error getting documents: ", task.getException());
                        }
                    }
                });

               for (int i=0;i<list.size();i++){
                   add_order(list,i);
               }

            }
        });

        txt_total=findViewById(R.id.total);
        list.clear();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(user_picked_items.this);

        progressDialog.setMessage("Loading Data Please Wait . . .");

        progressDialog.show();

        db.collection("taken_orders").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){
                    int count = 0;
                    progressDialog.dismiss();
                    for (DocumentSnapshot documentSnapshot : task.getResult()){
                        Items_list picklist =documentSnapshot.toObject(Items_list.class);
                            list.add(picklist);
                        count++;
                    }
                    try {

                        total = 0;
                        for (int i = 0; i < list.size(); i++) {
                            Items_list myObject = list.get(i);
                            total += Double.parseDouble(myObject.getTotal());
                        }
                        txt_total.setText("Total Amount "+String.valueOf(String.format("%.2f", total)));

                    }
                    catch ( Exception e ){


                    }
                    adapter = new picked_items_viewholder(user_picked_items.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(user_picked_items.this, e.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void add_order(ArrayList<Items_list> items_lists,int i){
        Map<String, Object> product_entry = new HashMap<>();
        product_entry.put("Name", items_lists.get(i).getName());
        product_entry.put("Qty", items_lists.get(i).getQty());
        product_entry.put("Total", items_lists.get(i).getTotal());
           db.collection("Orders").add(product_entry).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}