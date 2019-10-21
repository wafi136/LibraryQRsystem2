package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class frbsDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frbs_demo);

        Toast.makeText(frbsDemo.this,"FireBase connection Mau",Toast.LENGTH_LONG).show();
    }
}
