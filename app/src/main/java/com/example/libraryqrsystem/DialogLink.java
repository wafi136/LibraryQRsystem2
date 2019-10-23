package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DialogLink extends AppCompatActivity {
    String fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_link);

        TextView Display = findViewById(R.id.txtBoxInfo);
        TextView Display2 = findViewById(R.id.txtBoxBook);
        Bundle bn = getIntent().getExtras();
        String book = bn.getString("Book");
        Display.setText(String.valueOf(book));
    }
}
