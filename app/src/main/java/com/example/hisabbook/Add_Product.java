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

public class Add_Product extends Fragment {
    private EditText Category;
    private EditText Product_Name;
    private EditText Product_Code;
    private EditText Size;
    EditText Cost_Price;
    EditText MRP;
    EditText Date;
    EditText Vender;
    EditText Quantity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_product, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final String firebaseid = mAuth.getCurrentUser().getUid();

        Category = view.findViewById(R.id.Select_Category_id);
        Product_Code = view.findViewById(R.id.Product_Code_id);
        Product_Name = view.findViewById(R.id.Product_Name_id);
        Size = view.findViewById(R.id.Sizey_id);
        Cost_Price = view.findViewById(R.id.Cost_Price_id);
        MRP = view.findViewById(R.id.MRP_id);
        Date = view.findViewById(R.id.Daet_id);
        Quantity = view.findViewById(R.id.Qunatity_id);
        Vender = view.findViewById(R.id.Vender_id);


        Button fab = view.findViewById(R.id.button_add_product);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Category_var = Category.getText().toString();
                String Product_var = Product_Code.getText().toString();
                String Product_Name_var = Product_Name.getText().toString();
                String Size_var = Size.getText().toString();
                String Cost_Price_var = Cost_Price.getText().toString();
                String MRP_var = MRP.getText().toString();
                String Date_var = Date.getText().toString();
                String Quantity_var = Quantity.getText().toString();
                String Vender_var = Vender.getText().toString();

                DocumentReference dr = fstore.collection("users").document(firebaseid).collection("Categories").document(Category_var);
                Map<String, Object> user_categ = new HashMap<>();
                user_categ.put("Category", Category_var);
                dr.set(user_categ);

                DocumentReference dr_products=dr.collection("Product").document(Product_Name_var);
                Map<String, Object> user = new HashMap<>();
                user.put("Phone Code", Product_var);
                user.put("Product Name", Product_Name_var);
                user.put("Size", Size_var);
                user.put("Cost Price", Cost_Price_var);
                user.put("MRP", MRP_var);
                user.put("Last Bought", Date_var);
                user.put("Quantity", Quantity_var);
                user.put("Venders", Vender_var);


                DocumentReference dr_vend = fstore.collection("users").document(firebaseid).collection("Vender").document(Vender_var);
                Map<String, Object> user_vender = new HashMap<>();
                user_categ.put("Category", Category_var);
                dr_vend.set(user_vender);

                DocumentReference dr_vender_prod=dr_vend.collection("Products").document(Product_Name_var);
                Map<String, Object> user_vend = new HashMap<>();
                user_vend.put("Category", Category_var);
                user_vend.put("Phone Code", Product_var);
                user_vend.put("Product Name", Product_Name_var);
                user_vend.put("Size", Size_var);
                user_vend.put("Cost Price", Cost_Price_var);
                user_vend.put("MRP", MRP_var);
                user_vend.put("last bought", Date_var);
                user_vend.put("Quantity", Quantity_var);
                user_vend.put("Venders", Vender_var);

                dr_vender_prod.set(user_vender);




                dr_products.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Product Added", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_nav_add_prod_to_nav_default);


                    }
                });
            }
            });

        }}


