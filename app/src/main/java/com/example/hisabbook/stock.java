package com.example.hisabbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

public class stock extends AppCompatActivity implements Adapter.OnClicker {
    String transferrable[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final String firebaseid = mAuth.getCurrentUser().getUid();

        final RecyclerView category= findViewById(R.id.stock_list);
        category.setLayoutManager(new LinearLayoutManager(this));
        DocumentReference dr = fstore.collection("users").document(firebaseid);
        dr.collection("Categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                    }
                    String transferrable[]= list.toArray(new String[0]);
                    category.setAdapter(new Adapter(stock.this,transferrable));
                }}});

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(stock.this, "position is "+position, Toast.LENGTH_SHORT).show();

        Intent tntodetail=new Intent(stock.this,stock_product.class);
        tntodetail.putExtra("stock_product_name",transferrable[position]);
        startActivity(tntodetail);



    }
}





