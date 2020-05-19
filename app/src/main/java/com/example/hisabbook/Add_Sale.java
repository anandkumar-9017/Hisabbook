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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hisabbook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_Sale extends Fragment {
    private EditText Customer_name;
    private EditText Customer_phone;
    private EditText Product_Name;
    private EditText Size;
    TextView Cost_Price;
    EditText Selling_Price;
    EditText Date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add__sale, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final String firebaseid = mAuth.getCurrentUser().getUid();

        Customer_name=view.findViewById(R.id.sel_cust_name_id);
        Customer_phone=view.findViewById(R.id.Cust_phone_Number_id);
        Product_Name=view.findViewById(R.id.Sel_product_id);
        Size=view.findViewById(R.id.sel_size_id);
        Cost_Price=view.findViewById(R.id.sel_cp_id);
        Selling_Price=view.findViewById(R.id.Sel_sp_id);
        Date=view.findViewById(R.id.date_id);




        Button fab = view.findViewById(R.id.button_add_sale);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Customer_name_var=Customer_name.getText().toString();
                String Customer_phone_var=Customer_phone.getText().toString();
                String Product_Name_var=Product_Name.getText().toString();
                String Size_var=Size.getText().toString();
                String Cost_Price_var=Cost_Price.getText().toString();
                String Selling_Price_var=Selling_Price.getText().toString();
                String Date_var=Date.getText().toString();

                DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Sale").document(Date_var);
                Map<String, Object> user_sale_new = new HashMap<>();
                user_sale_new.put("Customer_name", Customer_name_var);
                dr.set(user_sale_new);


                DocumentReference dr_cust_sale = dr.collection("Customers").document(Customer_name_var);
                Map<String, Object> user = new HashMap<>();
                user.put("Customer_name", Customer_name_var);
                user.put("Phone Number", Customer_phone_var);
                user.put("Product Name", Product_Name_var);
                user.put("Size", Size_var);
                user.put("Cost Price", Cost_Price_var);
                user.put("Selling Price", Selling_Price_var);
                user.put("Date",Date_var);


                DocumentReference dr_cust = fstore.collection("users").document(firebaseid).collection("Customers").document(Customer_name_var);
                Map<String, Object> user_cust_new = new HashMap<>();
                user_cust_new.put("Customer_name", Customer_name_var);
                dr_cust.set(user_cust_new);

                DocumentReference dr_cust_dates = dr_cust.collection("Date").document(Date_var);
                Map<String, Object> user_cust = new HashMap<>();
                user_cust.put("Customer_name", Customer_name_var);
                user_cust.put("Phone Number", Customer_phone_var);
                user_cust.put("Product Name", Product_Name_var);
                user_cust.put("Size", Size_var);
                user_cust.put("Cost Price", Cost_Price_var);
                user_cust.put("Selling Price", Selling_Price_var);
                user_cust.put("Date",Date_var);
                dr_cust_dates.set(user_cust);







                dr_cust_sale.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"Sale Added",Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_button_add_sale_to_nav_total_sale);


                    }
                });


            }
        });

    }
}