package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class frbsDemo extends AppCompatActivity {
    EditText txtname, txtbookname;
    Button btnSend;
    Member member;

    DatabaseReference firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frbs_demo);

        txtname= (EditText)findViewById(R.id.editText4);
        txtbookname = (EditText)findViewById(R.id.editText5);
        btnSend = (Button) findViewById(R.id.btnSend);
        member = new Member();
        firebaseDB = FirebaseDatabase.getInstance().getReference().child("BookBorrowed by:");
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setName(txtname.getText().toString().trim());
                member.setBookName(txtbookname.getText().toString().trim());
                firebaseDB.push().setValue(member);
                Toast.makeText(frbsDemo.this,"Mau di Insert",Toast.LENGTH_LONG).show();

            }
        });
    }
}
