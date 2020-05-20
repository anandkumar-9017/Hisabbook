package com.example.hisabbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Customer_detail extends AppCompatActivity {
    private TextView name;
    private String collect;
    private TextView number;
    private TextView address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        name = findViewById(R.id.customer_display_name);
        number = findViewById(R.id.worker_display_phone);
        address = findViewById(R.id.customer_display_address);


        collect=getIntent().getExtras().get("Customer_name").toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fstore = FirebaseFirestore.getInstance();

        String firebaseid = mAuth.getCurrentUser().getUid();
        DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Customers").document(collect);
        dr.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            name.setText(documentSnapshot.getString("Customer Name"));
                            number.setText(documentSnapshot.getString("Phone Number"));
                            address.setText(documentSnapshot.getString("Address"));


                        }
                    }
                });


    }
}




