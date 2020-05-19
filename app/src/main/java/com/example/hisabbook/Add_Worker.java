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

public class Add_Worker extends Fragment {
    EditText Worker_name;
    EditText Worker_phone;
    EditText Worker_address;
    EditText Worker_salary;
    EditText Worker_code;
    EditText Worker_age;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final String firebaseid = mAuth.getCurrentUser().getUid();

        Worker_name=view.findViewById(R.id.worker_name_id);
        Worker_phone=view.findViewById(R.id.worker_phone_Number_id);
        Worker_code=view.findViewById(R.id.worker_code_id);
        Worker_salary=view.findViewById(R.id.worker_salary_id);
        Worker_age=view.findViewById(R.id.worker_age_id);
        Worker_address=view.findViewById(R.id.Worker_add_id);




        Button fab = view.findViewById(R.id.button_add_w);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String worker_name_var=Worker_name.getText().toString();
                String worker_phone_var=Worker_phone.getText().toString();
                String worker_code_var=Worker_code.getText().toString();
                String worker_salary_var=Worker_salary.getText().toString();
                String worker_age_var=Worker_age.getText().toString();
                String worker_address_var=Worker_address.getText().toString();

                DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Workers").document(worker_name_var);
                Map<String, Object> user = new HashMap<>();
                user.put("Worker Name", worker_name_var);
                user.put("Phone Number", worker_phone_var);
                user.put("Code", worker_code_var);
                user.put("Salary", worker_salary_var);
                user.put("Age", worker_age_var);
                user.put("Address", worker_address_var);


                dr.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"Worker Added",Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_button_add_worker_to_nav_workers);


                    }
                });


            }
        });

    }
}