package com.example.hisabbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.hisabbook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends Fragment {
    private EditText prof_name;
    private EditText prof_phone;
    private TextView prof_email;
    private EditText prof_shop;
    private EditText prof_gst;
    private EditText prof_shop_Address;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);


        return root;}
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            final NavController navController = Navigation.findNavController(view);
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseFirestore fstore = FirebaseFirestore.getInstance();
            prof_name=view.findViewById(R.id.Owner_name_edit);
            prof_shop=view.findViewById(R.id.owner_shop_name_edit);
            prof_phone=view.findViewById(R.id.Owner_phone_number_edit);
            prof_email=view.findViewById(R.id.owner_emailadsress_edit);
            prof_gst=view.findViewById(R.id.owner_gst_number_edit);
            prof_shop_Address=view.findViewById(R.id.owner_shop_address_edit);
            String firebaseid = mAuth.getCurrentUser().getUid();
            final DocumentReference dr = fstore.collection("users").document(firebaseid);
            dr.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()){
                                prof_name.setText(documentSnapshot.getString("User Name"));
                                prof_shop.setText(documentSnapshot.getString("Shop Name"));
                                prof_phone.setText(documentSnapshot.getString("Phone Number"));
                                prof_email.setText(documentSnapshot.getString("Email Id"));
                            prof_gst.setText(documentSnapshot.getString("Gst Number"));
                            prof_shop_Address.setText(documentSnapshot.getString("Shop Address"));}}});

                                Button fab = view.findViewById(R.id.button_update_profile);
                                fab.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String prof_name_var=prof_name.getText().toString();
                                        String prof_shop_var=prof_shop.getText().toString();
                                        String prof_email_var=prof_phone.getText().toString();
                                        String prof_phone_var=prof_name.getText().toString();
                                        String prof_gst_var=prof_gst.getText().toString();
                                        String prof_shop_Address_var=prof_shop_Address.getText().toString();





                                Map<String, Object> user = new HashMap<>();
                                user.put("User Name", prof_name_var);
                                user.put("Shop Name", prof_shop_var);
                                user.put("Phone Number", prof_phone_var);
                                user.put("Email Id", prof_email_var);
                                user.put("Gst Number", prof_gst_var);
                                user.put("Shop Address", prof_shop_Address_var);

                                dr.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getActivity(),"user Profile updated",Toast.LENGTH_SHORT).show();
                                        navController.navigate(R.id.action_button_edit_profile_to_nav_profile);


                                    }
                                });


                            }
                        });
                        }
                    }



