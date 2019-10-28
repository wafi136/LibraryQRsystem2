package com.example.libraryqrsystem;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText UserEmail, password;
    Button btnLogin;
    TextView tvReg;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        UserEmail = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPsd);
        btnLogin = findViewById(R.id.button);
        tvReg = findViewById(R.id.txtRegister);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(Login.this, "You already log in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, BookScan.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Login.this, "please log", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = UserEmail.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {
                    UserEmail.setError("Enter email");
                    UserEmail.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Enter password");
                    password.requestFocus();
                } else {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login Incorrect", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intToMenu = new Intent(Login.this, BookScan.class);
                                startActivity(intToMenu);
                            }
                        }
                    });

                }
            }
        });
        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registering = new Intent(Login.this,Register.class);
                startActivity(Registering);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}