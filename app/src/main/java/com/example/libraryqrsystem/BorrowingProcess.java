package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BorrowingProcess extends AppCompatActivity {
    EditText txtname, txtbookname;
    Button btnConfirm;
    Member borrower;

    DatabaseReference firebaseDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowing_process);


        txtname= (EditText)findViewById(R.id.txtName);
        txtbookname = (EditText)findViewById(R.id.txtBook);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        borrower = new Member();
        firebaseDB = FirebaseDatabase.getInstance().getReference().child("BookBorrowed by:");
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrower.setName(txtname.getText().toString().trim());
                borrower.setBookName(txtbookname.getText().toString().trim());
                firebaseDB.push().setValue(borrower);
                Toast.makeText(BorrowingProcess.this,"Process Complete",Toast.LENGTH_LONG).show();


            }
        });
    }
}
