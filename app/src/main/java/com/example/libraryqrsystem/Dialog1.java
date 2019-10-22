package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Dialog1 extends AppCompatActivity {

    EditText email;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog1);
        email = findViewById(R.id.txtEmail);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailforSend = email.getText().toString();
                Intent sendToDlgLink = new Intent(Dialog1.this,DialogLink.class);
                sendToDlgLink.putExtra("Book_Name",EmailforSend);
                startActivity(sendToDlgLink);
            }
        });

    }
}
