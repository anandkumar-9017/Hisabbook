package com.example.hisabbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Vender_prod extends AppCompatActivity implements Adapter.OnClicker {
    String transferrable[],collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        collect=getIntent().getExtras().get("vender_name").toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final RecyclerView vender_prod= (RecyclerView) findViewById(R.id.vender_prod_list);
        String firebaseid = mAuth.getCurrentUser().getUid();
        DocumentReference dr = fstore.collection("users").document(firebaseid);
       dr.collection("Venders").document(collect).collection("Products")
      .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                    }
                    transferrable= list.toArray(new String[0]);    }}});


                    vender_prod.setAdapter(new Adapter(Vender_prod.this,transferrable));




    }
    @Override
    public void onItemClick(int position) { Toast.makeText(Vender_prod.this, "position is "+position, Toast.LENGTH_SHORT).show();

        Intent tntodetail=new Intent(Vender_prod.this,Vender_product_detail.class);
        tntodetail.putExtra("vender_name",collect);
        tntodetail.putExtra("vender_prod",transferrable[position]);
        startActivity(tntodetail);


    }
}








