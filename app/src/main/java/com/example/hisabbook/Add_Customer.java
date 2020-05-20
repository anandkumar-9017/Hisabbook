package com.example.hisabbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_Customer extends Fragment {
    EditText customer_name;
    EditText customer_phone;
    EditText customer_address;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add__customer, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final String firebaseid = mAuth.getCurrentUser().getUid();

        customer_name=view.findViewById(R.id.edit_text_name);
        customer_phone=view.findViewById(R.id.edit_phone_number);
        customer_address=view.findViewById(R.id.edit_address);


        Button fab = view.findViewById(R.id.button_add_customer);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customer_name_var=customer_name.getText().toString();
                String customer_phone_var=customer_phone.getText().toString();
                String customer_address_var=customer_address.getText().toString();

                DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Customers").document(customer_name_var);
                Map<String, Object> user = new HashMap<>();
                user.put("Customer Name", customer_name_var);
                user.put("Phone Number", customer_phone_var);
                user.put("Address", customer_address_var);


                dr.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"Customer Added",Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_button_add_customer_to_nav_customer);


                    }
                });


            }
        });

    }
}