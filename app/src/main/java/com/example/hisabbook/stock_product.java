package com.example.hisabbook;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hisabbook.Adapter;
import com.example.hisabbook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class stock_product extends AppCompatActivity implements Adapter.OnClicker {
    String collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_product);
        collect=getIntent().getExtras().get("stock_product_name").toString();


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final String firebaseid = mAuth.getCurrentUser().getUid();


        final RecyclerView category= findViewById(R.id.stock_list);
        category.setLayoutManager(new LinearLayoutManager(this));
        DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Products").document(collect);
        dr.collection("Categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                    }
                    String transferrable[]= list.toArray(new String[0]);
                    category.setAdapter(new Adapter(stock_product.this,transferrable));
                }}});

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(stock_product.this, "position is "+position, Toast.LENGTH_SHORT).show();


    }
}





