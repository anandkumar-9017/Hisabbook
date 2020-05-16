package com.example.hisabbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Registration extends AppCompatActivity {
    EditText Emailid, Password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mfirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        mfirebaseAuth = FirebaseAuth.getInstance();

        Emailid = findViewById(R.id.draw);
        Password = findViewById(R.id.editText2);
        btnSignUp = findViewById(R.id.button2);
        tvSignIn = findViewById(R.id.textView);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            private int Text;

            @Override
            public void onClick(View V) {
                String email = Emailid.getText().toString();
                String pwd = Password.getText().toString();
                if (email.isEmpty()) {
                    Emailid.setError("Please enter Email id");
                    Emailid.requestFocus();
                } else if (pwd.isEmpty()) {
                    Password.setError("please enter your password");
                    Password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(Registration.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mfirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mfirebaseAuth.getCurrentUser();
                                Intent toHome = new Intent(Registration.this, Dashboard.class);
                                startActivity(toHome);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Registration.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
                } else {
                    // Toast.makeText( Registration.this, Text, "Error Occurred!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(Registration.this, "Error Occurred!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}


