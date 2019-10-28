package com.example.libraryqrsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText UserEmail, Password;
    Button btnReg;
    TextView tvLog;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        UserEmail = findViewById(R.id.txtEmail);
        Password = findViewById(R.id.txtPsd);
        tvLog = findViewById(R.id.txtRegister);
        btnReg = findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = UserEmail.getText().toString();
                String Psd = Password.getText().toString();
                if (Email.isEmpty()) {
                    UserEmail.setError("Please enter your Email");
                    UserEmail.requestFocus();
                } else if (Psd.isEmpty()) {
                    Password.setError("Please enter your password");
                    Password.requestFocus();
                } else if (Email.isEmpty() && Psd.isEmpty()) {
                    Toast.makeText(Register.this, "Field empty", Toast.LENGTH_SHORT).show();
                } else if (!(Email.isEmpty() && Psd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(Email, Psd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, " SignUp Unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Register.this, BookScan.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register.this, " Error Happen", Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserLogin = new Intent(Register.this,Login.class);
                startActivity(UserLogin);
            }
        });
    }
}