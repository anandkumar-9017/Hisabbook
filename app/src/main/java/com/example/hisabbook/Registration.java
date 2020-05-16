package com.example.hisabbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {
    EditText userid;
    EditText password;
    Button signup;
    TextView login;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        userid = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        signup = findViewById(R.id.reg);
        login = findViewById(R.id.backtologin);
        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("SignUp");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = userid.getText().toString();
                String pass = password.getText().toString();

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
                                Toast.makeText(Registration.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intToHome = new Intent(Registration.this, navigation.class);
                                startActivity(intToHome);
                            }

                        }
                    });
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToReg = new Intent(Registration.this, MainActivity.class);
                startActivity(intToReg);

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