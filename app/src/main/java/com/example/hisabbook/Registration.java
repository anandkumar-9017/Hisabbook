package com.example.hisabbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    EditText userid;
    EditText password;
    EditText name,shop_name,phone_number;
    Button signup;
    TextView login;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        userid = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        name=findViewById(R.id.name);
        shop_name=findViewById(R.id.Shop_name);
        phone_number=findViewById(R.id.phone_number);
        signup = findViewById(R.id.reg);
        login = findViewById(R.id.backtologin);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        getSupportActionBar().setTitle("SignUp");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id = userid.getText().toString();
                String pass = password.getText().toString();
                final String user_name=name.getText().toString();
                final String user_number=phone_number.getText().toString();
                final String user_shop=shop_name.getText().toString();

                if (id.isEmpty() && pass.isEmpty()) {
                    userid.setError("Please Enter the user id");   //for setting an error if user didn't entered user id
                    userid.requestFocus();                        // then focus the edit text
                    password.setError("Please Enter the password");   //for setting an error if user didn't entered user id
                    password.requestFocus();                        // then focus the edit text
                } else if (id.isEmpty()) {
                    userid.setError("Please Enter the user id");   //for setting an error if user didn't entered user id
                    userid.requestFocus();                        // then focus the edit text
                } else if (pass.isEmpty()) {
                    password.setError("Please Enter the password");   //for setting an error if user didn't entered user id
                    password.requestFocus();                        // then focus the edit text
                } else {
                    mAuth.createUserWithEmailAndPassword(id, pass).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Registration.this, "User Not Registered", Toast.LENGTH_SHORT).show();


                            } else {
                                String firebaseid = mAuth.getCurrentUser().getUid();
                                DocumentReference dr = fstore.collection("users").document(firebaseid);
                                Map<String, Object> user = new HashMap<>();
                                user.put("User Name", user_name);
                                user.put("Shop Name", user_shop);
                                user.put("Phone Number", user_number);
                                user.put("Email Id", id);
                                user.put("Gst number", "");
                                user.put("Shop Address", "");

                                dr.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Registration.this,"User Registered",Toast.LENGTH_SHORT).show();
                                        Intent inttonavigation=new Intent(Registration.this,navigation.class);
                                        startActivity(inttonavigation);
                                    }
                                }) .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Registration.this,"Error Occured",Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }

                        }
                    });
                }
            }
        });


    }
 @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
 }


        }
