package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DialogLink extends AppCompatActivity {
    String fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_link);

        Intent received=getIntent();
        fullname= received.getStringExtra("Book_Detail");
        getSupportActionBar().setTitle(fullname);
    }
}
