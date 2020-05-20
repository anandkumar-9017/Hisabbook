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

public class Vender_product_detail extends AppCompatActivity  {
    private TextView vender_name;
    private String collect;
    private String collect2;
    private TextView vender_code;
    private TextView vender_cp;
    private TextView vender_sp;
    private TextView vender_size;
    private TextView vender_quantity;
    private TextView vender_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vender_prod_detail);
        vender_name = findViewById(R.id.vender_product_list_product_ed);
        vender_code = findViewById(R.id.vender_sale_Product_code_ed);
        vender_cp = findViewById(R.id.vender_cp_ed);
        vender_sp = findViewById(R.id.Vender_MRP_ed);
        vender_size = findViewById(R.id.Vender_size_ed);
        vender_quantity = findViewById(R.id.vender_product_list_quantity_ed);
        vender_date = findViewById(R.id.Vender_date_ed);
        collect=getIntent().getExtras().get("vender_name").toString();
        collect2=getIntent().getExtras().get("vender_prod").toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fstore = FirebaseFirestore.getInstance();

        String firebaseid = mAuth.getCurrentUser().getUid();
        DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Vender").document(collect).collection("Products").document(collect2);
        dr.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            vender_name.setText(documentSnapshot.getString("Product Name"));
                            vender_code.setText(documentSnapshot.getString("Phone Code"));
                            vender_cp.setText(documentSnapshot.getString("Cost Price"));
                            vender_sp.setText(documentSnapshot.getString("MRP"));
                            vender_size.setText(documentSnapshot.getString("Size"));
                            vender_quantity.setText(documentSnapshot.getString("Quantity"));
                            vender_date.setText(documentSnapshot.getString("last bought"));


                        }
                    }
                });


    }
}





