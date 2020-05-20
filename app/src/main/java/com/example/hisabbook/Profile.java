package com.example.hisabbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.hisabbook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Profile extends Fragment {
    private TextView prof_name;
    private TextView prof_phone;
    private TextView prof_email;
    private TextView prof_shop;
    private TextView prof_gst;
    private TextView prof_shop_Address;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        prof_name=view.findViewById(R.id.Owner_name);
        prof_shop=view.findViewById(R.id.owner_shop_name);
        prof_phone=view.findViewById(R.id.Owner_phone_number);
        prof_email=view.findViewById(R.id.owner_emailadsress);
        prof_gst=view.findViewById(R.id.owner_gst_number);
        prof_shop_Address=view.findViewById(R.id.owner_shop_address);
        String firebaseid = mAuth.getCurrentUser().getUid();
        DocumentReference dr = fstore.collection("users").document(firebaseid);
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
                            prof_shop_Address.setText(documentSnapshot.getString("Shop Address"));


                        }
                    }
                });

        FloatingActionButton fab = view.findViewById(R.id.button_edit_profile);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_profile_to_button_edit_profile);


            }
        });

    }
}
