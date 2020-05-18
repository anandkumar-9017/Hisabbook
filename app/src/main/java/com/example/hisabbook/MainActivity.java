package com.example.hisabbook;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    EditText userid;
    EditText password;
    Button login;
    Button skip;
    TextView Register;
    TextView forgot;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userid = findViewById(R.id.draw);
        password = findViewById(R.id.editText2);
        skip=findViewById(R.id.button3);
        login = findViewById(R.id.button2);
        Register = findViewById(R.id.textView3);
        forgot = findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(MainActivity.this, "You are successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, navigation.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "You are not logged in", Toast.LENGTH_SHORT).show();
                }


            }

        };
        login.setOnClickListener(new View.OnClickListener() {
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
                    mAuth.signInWithEmailAndPassword(id, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intToHome = new Intent(MainActivity.this, navigation.class);
                                startActivity(intToHome);
                            }

                        }
                    });
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skip= new Intent(MainActivity.this,navigation.class);
                startActivity(skip);
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToReg = new Intent(MainActivity.this, Registration.class);
                startActivity(intToReg);

            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PasswordActivity.class));
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag){
            finish();
            Toast.makeText(MainActivity.this, "log in successful",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,Dashboard.class ));
        }else {
            Toast.makeText(MainActivity.this,"Verify your Email Id ",Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
        }
    }




    }
