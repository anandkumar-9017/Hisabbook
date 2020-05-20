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

public class Worker_display_detail extends AppCompatActivity {
    private TextView worker_name;
    private String collect;
        private TextView worker_number;
    private TextView worker_salary;
    private TextView worker_age;
    private TextView worker_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_worker_detail);
        worker_name = findViewById(R.id.worker_display_name);
        worker_number = findViewById(R.id.worker_display_phone);
        worker_salary = findViewById(R.id.worker_display_salary);
        worker_age = findViewById(R.id.worker_display_age);
        worker_address = findViewById(R.id.worker_display_address);
        collect=getIntent().getExtras().get("worker_name").toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fstore = FirebaseFirestore.getInstance();

        String firebaseid = mAuth.getCurrentUser().getUid();
        DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Workers").document(collect);
        dr.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            worker_name.setText(documentSnapshot.getString("Worker Name"));
                            worker_number.setText(documentSnapshot.getString("Phone Number"));
                            worker_salary.setText(documentSnapshot.getString("Salary"));
                            worker_age.setText(documentSnapshot.getString("Age"));
                            worker_address.setText(documentSnapshot.getString("Address"));


                        }
                    }
                });


    }
}






