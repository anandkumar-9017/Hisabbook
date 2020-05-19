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

public class Add_Vender extends Fragment {
    EditText vender_name;
    EditText vender_phone;
    EditText vender_address;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add__vender, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final String firebaseid = mAuth.getCurrentUser().getUid();

        vender_name=view.findViewById(R.id.vender_name_add);
        vender_phone=view.findViewById(R.id.vender_phone_add);
        vender_address=view.findViewById(R.id.vender_address_add);


        Button fab = view.findViewById(R.id.button_add_vender);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vender_name_var=vender_name.getText().toString();
                String vender_phone_var=vender_phone.getText().toString();
                String vender_address_var=vender_address.getText().toString();

                DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Venders").document(vender_name_var);
                Map<String, Object> user = new HashMap<>();
                user.put("Vender Name", vender_name_var);
                user.put("Phone Number", vender_phone_var);
                user.put("Address", vender_address_var);


                dr.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"Vender Added",Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_button_add_vender_to_nav_venders);


                    }
                });


            }
        });

    }
}
